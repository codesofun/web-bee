package bee;

import bee.download.DownLoader;
import bee.linker.Page;
import bee.linker.Request;
import bee.processor.PageProcessor;
import bee.processor.Setting;

/**
 * webBee框架核心入口
 *
 * data 2017-03-23   01:06
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Bee implements Runnable{

    private PageProcessor pageProcessor;

    private DownLoader downLoader;

    private Request request;

    private Setting setting;

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

        System.out.println("this is Bee.class implement Runnable's run function! --request:" + request);
        pageProcessor.process(pageProcessor(request));
    }

    /**
     * 获取带抓取请求信息
     * @return
     */
    public void requestProcessor(){
        this.request = new Request(setting.getStartUrl());
    }

    public Page pageProcessor(Request request){
        return downLoader.download(request, this);
    }
}
