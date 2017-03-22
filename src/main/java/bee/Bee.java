package bee;

import bee.processor.PageProcessor;

/**
 * webBee框架核心入口
 *
 * data 2017-03-23   01:06
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Bee implements Runnable{

    private PageProcessor pageProcessor;

    /**
     * 实例化处理规则
     * @param pageProcessor 用来指向指针到具体规则实现类
     */
    public Bee(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
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
        System.out.println("this is Bee.class implement Runnable's run function!");
    }
}
