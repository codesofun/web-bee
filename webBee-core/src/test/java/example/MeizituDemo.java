package example;

import org.bee.webBee.Bee;
import org.bee.webBee.handler.ConsoleHandler;
import org.bee.webBee.handler.FileDownloadHandler;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;

import java.io.IOException;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/15 23:58
 */
public class MeizituDemo implements PageProcessor{

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {
        List<String> photoUrl = page.getHtml().$("#wrapper #maincontent  div.postContent #picture").getLinks();
        page.addWaitRequest(photoUrl);
        List<String> nextUrl = page.getHtml().$("#wp_page_numbers ul li").getLinks();
        page.addWaitRequest(nextUrl);

        List<String> imageUrl = page.getHtml().$("#wrapper #maincontent #picture").getImgUrls();
        page.setFileResult(imageUrl);

    }

    @Override
    public Setting getSetting() {
        setting = Setting.create();
        setting.setStartUrl("http://www.meizitu.com")
                .setDomain("http://www.meizitu.com")
                .setHttpMethod("GET")
                .setCookies("__jsluid","4aba900eae2481a58f890b7e766df71d")
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.98 Safari/537.36")
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate, sdch, br")
                .addHeader("Accept-Language","zh-CN, zh; q=0.8, en; q=0.6")
                .setThreadSleep(2000)
                .setThreadNum(3);
        return setting;
    }

    public static void main(String[] args) {
        Bee.create(new MeizituDemo())
                .setHandler(new FileDownloadHandler("/Users/wangtonghe/workspace/data/java/javaBee/beauty"))
                .setHandler(new ConsoleHandler())
                .run();
    }
}
