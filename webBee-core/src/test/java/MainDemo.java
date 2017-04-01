/**
 * Created by zhuang on 2017/3/23.
 */



import com.alibaba.fastjson.JSON;
import org.bee.webBee.Bee;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;
import org.jsoup.select.Elements;

/**
 * data 2017-03-23   01:19
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */

public class MainDemo implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) {
        //todo page.getJson/html/string().$('textarea.content').as('content').bulid().$('#img').as('img')
        //todo 期望结果: {content:[],img:[]} 一条{}多条[] 的json格式
        //todo page.nextUrl('span>ss>s')
        //todo 直接获取api接口
        Elements elements = page.getHtml().$("textarea.content").all();

        System.out.println("This is MainDemo's process function ...");
        System.out.println(elements.toString());

        String strJson= JSON.toJSONString(elements.toString());
        System.out.println("------------");
        System.out.println(strJson);
    }

    @Override
    public Setting getSetting() {
        System.out.println("This is MainDemo's setting function ...");
        setting = Setting.create().setStartUrl("http://www.ZhiHu.com/explore");
        setting = setting.setCookies("z_c0","Mi4wQUFEQUZwSzEzZ2tBY01ERVdCM0lDUmNBQUFCaEFsVk5WQjcyV0FEb0RnYlI3QnFQSWtzSWMzTjRHQVN0YlNlTW1R");
        setting = setting.setCookies("_xsrf","8e8eedb720402d12bce9b5e611837b6d");
        setting = setting.setDomain("zhihu.com");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new MainDemo()).run();
    }
}
