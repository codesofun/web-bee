package example;

import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;

import java.io.IOException;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/6 12:16
 */
public class Demo163MusicDemo implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {

        List<String> musiclist = page.getHtml().$("ul.m-cvrlst  li .u-cover").getLinks();


    }

    @Override
    public Setting getSetting() {
        setting = Setting.create().setStartUrl("http://music.163.com/#/discover/playlist");
        setting.setDomain("http://music.163.com");
        setting.setHttpMethod("GET");
        setting.setCookies("TOKEN", "3soOOnSdtUGWNM0b");
        setting.setThreadSleep(1000);
        setting.setThreadNum(3);
        return setting;
    }

    public static void main(String[] args) {

    }
}

