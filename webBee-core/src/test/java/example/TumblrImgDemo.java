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
 * TODO 待写代理池去爬
 * @author wangtonghe
 * @date 2017/5/11 22:15
 */
public class TumblrImgDemo implements PageProcessor {

    private Setting setting;


    @Override
    public void process(Page page) throws IOException {
        List<String> imgUrls= page.getHtml().$("#posts .main .article .post").getImgUrls();
        page.setFileResult(imgUrls);


    }

    @Override
    public Setting getSetting() {
        setting=Setting.create().setStartUrl("https://qgl9899.tumblr.com");
        setting.setDomain("https://tumblr.com");
        setting.setHttpMethod("GET");
        setting.setThreadSleep(2000);
        setting.setThreadNum(6);
        return setting;
    }

    public static void main(String[] args) {
        Bee.create(new TumblrImgDemo())
                .setHandler(new FileDownloadHandler("/Users/wangtonghe/workspace/data/java/javaBee"))
                .setHandler(new ConsoleHandler())
                .run2();
    }
}
