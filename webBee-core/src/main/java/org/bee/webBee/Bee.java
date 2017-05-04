package org.bee.webBee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bee.webBee.download.HttpClientDownloader;
import org.bee.webBee.handler.BeeResults;
import org.bee.webBee.handler.ConsoleHandler;
import org.bee.webBee.handler.Handler;
import org.bee.webBee.html.Html;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.download.DownLoader;
import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Setting;
import org.bee.webBee.processor.Task;
import org.bee.webBee.utils.JsonUtil;
import org.bee.webBee.utils.UrlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    ExecutorService executorService;

    private Handler handler;

    protected AtomicInteger threadStatus = new AtomicInteger(0);


    private Request request;

    private Setting setting;

    private Queue<Request> waitRequests = new LinkedBlockingQueue<>();


    private static Integer COUNT = 0;

    private Html html;

    /**
     * 实例化处理规则
     *
     * @param pageProcessor 用来指向指针到具体规则实现类
     */
    public Bee(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
        this.setting = pageProcessor.getSetting(); //获取用户配置
        waitRequests.add(new Request(setting.getUrl()));  //设置开始路径
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
        requestProcessor();
        while (request != null) {
            if (COUNT >= 1 && request != null) {
                if (!requestNextProcessor()) break;
//                if(!checkResultData()) break;
            }
            COUNT++;
            System.out.println("this is Bee.class implement Runnable's run function! --request:" + request.toString());
            try {
                Thread.sleep(setting.getThreadSleep());
                pageProcessor.process(pageProcessor(request));
                System.out.println(" ");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void run2() {
        initThreadPool();
        while (!Thread.currentThread().isInterrupted() ) {
            Request request = waitRequests.poll();
            if (request == null) {
                if (((ThreadPoolExecutor) executorService).getActiveCount() == 0) {
                    break;
                }
                continue;
            }
            executorService.execute(() -> {
                Page page = pageProcessor(request);
                if (page.getStatusCode() == RequestConstant.STATUS_CODE_200) {
                    try {
                        pageProcessor.process(page);  //处理页面
                        handleResult(page);    //处理结果
                        Thread.sleep(setting.getThreadSleep());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void handleResult(Page page) {
        //默认使用Console处理结果，即打印到控制台
        if (handler == null) {
            handler = new ConsoleHandler();
        }
        handler.handle(page.getBeeResults());
        if(page.getWaitRequests()!=null&&page.getWaitRequests().size()>0){
            waitRequests.addAll(page.getWaitRequests());  //添加待处理url
        }

    }

    private void initThreadPool() {
        executorService = Executors.newFixedThreadPool(setting.getThreadNum());
    }


    public Page pageProcessor(Request request) {

        return downLoader.download(request, this); //annotation: this 'this' to substitute one expression with Task's implement
    }

    /**
     * 获取带抓取请求信息
     *
     * @return
     */
    public void requestProcessor() {
        if (setting.getNextUrlKeyOnResult() != null || COUNT < 1) this.request = new Request(setting.getUrl());


    }

    /**
     * 获取下一个带抓取请求信息
     *
     * @return todo paging next不可以写死
     */
    private boolean requestNextProcessor() {
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

    public Handler getHandler() {
        return handler;
    }

    public Bee setHandler(Handler handler) {
        this.handler = handler;
        return this;
    }
}
