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
 * @date 2017/5/12 00:39
 */
public class HuabanImgDemo implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {
        List<String> photoUrl = page.getHtml().$("#waterfall > div").getLinks();
        page.addWaitRequest(photoUrl);
        List<String> imageUrl = page.getHtml().$(".pin-view-wrapper .main-image a").getImgUrls();
        page.setFileResult(imageUrl);



    }

    @Override
    public Setting getSetting() {
        setting = Setting.create().setStartUrl("http://huaban.com/boards/17375733/?md=newbn&beauty");
        setting.setDomain("http://huaban.com");
        setting.setHttpMethod("GET");
        setting.setThreadSleep(2000);
        setting.setThreadNum(6);
        return setting;
    }

    public static void main(String[] args) {
        Bee.create(new HuabanImgDemo())
                .setHandler(new FileDownloadHandler("/Users/wangtonghe/workspace/data/java/javaBee/huaban2"))
                .setHandler(new ConsoleHandler())
                .run();
    }
}
