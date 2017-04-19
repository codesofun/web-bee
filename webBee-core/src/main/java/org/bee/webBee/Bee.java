package org.bee.webBee;

import org.bee.webBee.download.HttpClientDownloader;
import org.bee.webBee.html.Html;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.download.DownLoader;
import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Setting;
import org.bee.webBee.processor.Task;

import java.io.IOException;

/**
 * webBee框架核心入口
 *
 * data 2017-03-23   01:06
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Bee implements Runnable,Task {

    private PageProcessor pageProcessor;

    private DownLoader downLoader = new HttpClientDownloader();

    private Request request;

    private Setting setting;

    private static  Integer COUNT = 0;

    private Html html;
    /**
     * 实例化处理规则
     * @param pageProcessor 用来指向指针到具体规则实现类
     */
    public Bee(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
        this.setting = pageProcessor.getSetting(); //获取用户配置
    }

    /**
     * 新建一个处理器
     * @param pageProcessor 自定义的处理规则
     * @return
     */
    public static Bee create(PageProcessor pageProcessor){
        return new Bee(pageProcessor);
    }


    @Override
    public void run() {
        if(COUNT==0){
            requestProcessor();
        }else{
            requestNextProcessor();
        }

        System.out.println("this is Bee.class implement Runnable's run function! --request:" + request.toString());
        try {
            pageProcessor.process(pageProcessor(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取带抓取请求信息
     * @return
     */
    public void requestProcessor(){
        this.request = new Request(setting.getUrl());
        COUNT++;
    }

    public Page pageProcessor(Request request){

        return downLoader.download(request, this); //annotation: this 'this' to substitute one expression with Task's implement
    }

    /**
     * 获取下一个带抓取请求信息
     * @return
     */
    private void requestNextProcessor() {
        System.out.println("---"+html.getApi());
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
