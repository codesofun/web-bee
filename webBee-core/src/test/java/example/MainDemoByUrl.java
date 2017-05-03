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

public class MainDemoByUrl implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) {
        //todo page.getJson/html/string().$('textarea.content').as('content').bulid().$('#img').as('img')
        //todo 期望结果: {content:[],img:[]} 一条{}多条[] 的json格式
        //todo page.nextUrl('span>ss>s')
        //todo 直接获取api接口
        String json = page.getHtml().$("textarea.content").as("content").$("a.question_link").as("title").toJSONString();
//
        System.out.println(json);
    }

    @Override
    public Setting getSetting() {
        System.out.println("This is example.MainDemoByUrl's setting function ...");
        setting = Setting.create().setStartUrl("http://www.ZhiHu.com/explore");
        setting = setting.setCookies("z_c0","Mi4wQUFEQUZwSzEzZ2tBY01ERVdCM0lDUmNBQUFCaEFsVk5WQjcyV0FEb0RnYlI3QnFQSWtzSWMzTjRHQVN0YlNlTW1R");
        setting = setting.setCookies("_xsrf","8e8eedb720402d12bce9b5e611837b6d");
        setting = setting.setDomain("zhihu.com");
        setting = setting.setHttpMethod("GET");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new MainDemoByUrl()).run();
    }
}
