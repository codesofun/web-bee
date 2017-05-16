package org.bee.webBee;

import org.bee.webBee.download.DownLoader;
import org.bee.webBee.download.HttpClientDownloader;
import org.bee.webBee.html.Html;
import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;
import org.bee.webBee.processor.Task;

import org.bee.webBee.utils.JsonUtil;

import java.io.IOException;

import org.bee.webBee.thread.BeeThreadPool;
import org.bee.webBee.utils.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;


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

        initThreadPool();
        while (!Thread.currentThread().isInterrupted() ) {
             Request request = waitRequests.poll();
            if (request == null) {
                if (beeThreadPool.getAliveThreadNum()== 0) {
                    break;
                }
                continue;
            }
            beeThreadPool.execute(() -> {
                Page page = pageProcessor(request);
                if (page.getStatusCode() == BeeConstant.STATUS_CODE_200) {
                    try {
                        pageProcessor.process(page);  //处理页面
                        handleResult(page);    //处理结果
                        Thread.sleep(setting.getThreadSleep());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(page.getStatusCode()==BeeConstant.STATUS_CODE_403){
                    System.err.println(setting.getDomain()+"被封了！！！");
                    resetHeader();
                }
            });
        }
        close();
    }

    private void close(){
        beeThreadPool.shutdown();
        waitRequests.clear();
        handleResultEnd();

    }

    private void clearHeader(){
        setting.clearHeader();
    }

    private void resetHeader(){
        clearHeader();
        String userAgent = BeeConstant.userAgentList.get(new Random().nextInt( BeeConstant.userAgentList.size()));
        setting.addHeader("userAgent",userAgent);
        setting.setCookies("bid", StringUtil.getRandomStr(11));

    }

    private void handleResult(Page page) {
        //默认使用Console处理结果，即打印到控制台
        if (handlers == null) {
            handlers.add(new ConsoleHandler());
        }
        handlers.forEach((e)->{
            e.setDomain(domain);  //设置域名
            e.handle(page.getBeeResults());
        });
        if(page.getWaitRequests()!=null&&page.getWaitRequests().size()>0){
            waitRequests.addAll(page.getWaitRequests());  //添加待处理url
        }

    }

    private void handleResultEnd(){
        handlers.forEach(Handler::destory);
    }

    private void initThreadPool() {
        beeThreadPool = new BeeThreadPool(setting.getThreadNum());

    }


    public Page pageProcessor(Request request) {


        return downLoader.download(request, this); //annotation: this 'this' to substitute one expression with Task's implement
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
