/**
 * Created by zhuang on 2017/3/23.
 */

import bee_core.Bee;
import bee_core.annotation.HelloAnnotation;
import bee_core.html.Html;
import bee_core.html.Selector;
import bee_core.linker.Page;
import bee_core.processor.PageProcessor;
import bee_core.processor.Setting;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * data 2017-03-23   01:19
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */

public class MainDemo implements PageProcessor{

    private Setting setting;

    @Override
    public void process(Page page) {
        Elements elements = page.getHtml().$("textarea.content").all();
        System.out.println("This is MainDemo's process function ...");
        System.out.println(elements);
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
