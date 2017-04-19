package org.bee.webBee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        requestProcessor();
        while (request!=null  ){
            if(COUNT>1){

                requestNextProcessor();
            }
            System.out.println("this is Bee.class implement Runnable's run function! --request:" + request.toString());
            try {
                Thread.sleep(1000);
                pageProcessor.process(pageProcessor(request));
                System.out.println(" ");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public Page pageProcessor(Request request){

        return downLoader.download(request, this); //annotation: this 'this' to substitute one expression with Task's implement
    }

    /**
     * 获取带抓取请求信息
     * @return
     */
    public void requestProcessor(){
        this.request = new Request(setting.getUrl());
        COUNT++;
    }

    /**
     * 获取下一个带抓取请求信息
     * @return
     */
    private void requestNextProcessor() {
        this.request = new Request(setting.getUrl());
        JSONObject json = (JSONObject) JSON.parse(html.getApi());
        json.get(setting.getNextUrlKeyOnResult());
        System.out.println("---"+ ((JSONObject) JSON.parse(html.getApi())).get(setting.getNextUrlKeyOnResult()));
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
