package org.bee.webBee;

import org.bee.webBee.download.DownLoader;
import org.bee.webBee.download.FileDownloader;
import org.bee.webBee.download.HttpClientDownloader;
import org.bee.webBee.html.Html;
import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;
import org.bee.webBee.processor.Task;
import org.bee.webBee.thread.BeeExecutor;
import org.bee.webBee.thread.BeeExecutorPool;
import org.bee.webBee.utils.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * webBee框架核心入口
 * <p>
 * data 2017-03-23   01:06
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class Bee implements Runnable, Task {


    private PageProcessor pageProcessor;

    private DownLoader downLoader = new HttpClientDownloader();

    private Request request;

    private Setting setting;


    /**
     * 爬取网站域名
     */
    private String domain;


    private static Integer COUNT = 0;

    private Html html;

    /**
     * 默认线程数
     */
    private static Integer threadNum = 3;

    /**
     * 表示任务等待完成的 Future //todo 可能会放入page中
     */
    List<Future<String>> resultList = new ArrayList<Future<String>>();

    /**
     * 实例化处理规则
     *
     * @param pageProcessor 用来指向指针到具体规则实现类
     */
    public Bee(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
        this.setting = pageProcessor.getSetting(); //获取用户配置
    }

    /**
     * 新建一个处理器
     *
     * @param pageProcessor 自定义的处理规则
     * @return
     */
    public static Bee create(PageProcessor pageProcessor) {
        return new Bee(pageProcessor);
    }


    @Override
    public void run() {
        BeeExecutorPool beeThreadPool = new BeeExecutorPool(threadNum);
        requestProcessor();
        while (request != null || setting.getInfinite()) {
            if (COUNT >= 1 || setting.getInfinite()) {
                if (!requestNextProcessor()) break;
////                if(!checkResultData()) break;
            }
            COUNT++;
            System.out.println("this is Bee.class implement Runnable's run function! --request:" + request.toString());
//            beeThreadPool.execute(() -> {
                Page page = pageProcessor(request);
                if (page.getStatusCode() == BeeConstant.STATUS_CODE_200) {
                    try {
                        Thread.sleep(setting.getThreadSleep());
                        pageProcessor.process(page);
                        System.out.println(Thread.currentThread().getName() + "线程被调用了。");
                        if(page.getWaitRequests().size()>0){
                            page.getWaitRequests().iterator().forEachRemaining(request -> {
                                        beeThreadPool.execute(() -> {
                                            System.out.println("page.getWaitRequests()"+Thread.currentThread().getName() + "线程被调用了。");
                                            new FileDownloader(request, page.getTask()).downFileProcessor("/Users/zhuang/Desktop/",  String.valueOf((Math.random())) );
                                        });
//                                beeThreadPool.shutdown();
                            });
                        }
//                            handleResult(page);    //处理结果
//                            Thread.sleep(setting.getThreadSleep());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                    else if(page.getStatusCode()==BeeConstant.STATUS_CODE_403){
//                        System.err.println(setting.getDomain()+"被封了！！！");
//                        resetHeader();
//                    }
//            });
//                Thread.sleep(setting.getThreadSleep());
//                pageProcessor.process(pageProcessor(request));
//                System.out.println("");
        }

    }

    //todo 命名重读 讨论是否更改
    public Page pageProcessor(Request request) {


        return downLoader.download(request, this); //annotation: this 'this' to substitute one expression with Task's implement
    }

    /**
     * 获取带抓取请求信息
     *
     * @return
     */
    public void requestProcessor() {
        //todo 解析@targetUrl || Page里面解析
        if (setting.getNextUrlKeyOnResult() != null || COUNT < 1) this.request = new Request(setting.getUrl());
    }

    /**
     * 获取下一个带抓取请求信息
     *
     * @return
     */
    private boolean requestNextProcessor() {
        if(html == null) return false;
        String url = JsonUtil.jsonCustomKey(html.getJsonApi(), setting.getNextUrlKeyOnResult());
        if (url != null) {
            this.request = new Request(url);
            return true;
        }
        return false;
//        System.out.println("nextUrl--->"+ JsonUtil.jsonCustomKey(html.getJsonApi(),setting.getNextUrlKeyOnResult()));
    }


    @Override
    public Setting getSetting() {
        return setting;
    }

    @Override
    public void setHtml(Html html) {
        this.html = html;
    }


}
