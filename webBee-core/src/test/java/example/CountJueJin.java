package example; /**
 * Created by zhuang on 2017/3/23.
 */



import org.bee.webBee.Bee;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;


/**
 * 类似servlet 实现HttpServlet doGet doPost 方法的方式定义爬虫
 * data 2017-03-23   01:19
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */

public class CountJueJin implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) {
        //todo page.getJson/html/string().$('textarea.content').as('content').bulid().$('#img').as('img')
        //todo 期望结果: {content:[],img:[]} 一条{}多条[] 的json格式
        //todo page.nextUrl('span>ss>s')
        //todo 直接获取api接口

            String json = page.getHtml().$("a").as("title").toJSONString();
//
            System.out.println(json);


    }

    @Override
    public Setting getSetting() {
        System.out.println("This is example.MainDemoByUrl's setting function ...");
        setting = Setting.create().setStartUrl("https://juejin.im/user/5823d1a3a22b9d0067fde1f7");
        //添加cookie,模拟登陆  也可以选择setting.addCookie(key,value)添加cookie;
        setting = setting.addHeader("Set-Cookie","QINGCLOUDELB=6d0ab4ef124aa52bf550d3255fa396ebab7e48fb3972913efd95d72fe838c4fb|WebLd|WebLd; path=/");
        setting = setting.addHeader("Server","nginx/1.10.2");
        setting = setting.setDomain("juejin.im");
        setting = setting.setHttpMethod("GET");
        setting = setting.setInfinite(true);
        return setting;
    }



    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            try {
                Thread.sleep(5000L);
                Bee.create(new CountJueJin()).run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
