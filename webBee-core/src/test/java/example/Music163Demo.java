package example;

import org.bee.webBee.Bee;
import org.bee.webBee.handler.ConsoleHandler;
import org.bee.webBee.handler.JsonFileHandler;
import org.bee.webBee.html.Html;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;

import java.io.IOException;
import java.util.List;

/**
 * //TODO 获取动态生成的内容
 * @author wangtonghe
 * @date 2017/5/6 12:16
 */
public class Music163Demo implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {

//        List<String> list = page.getHtml().$("ul.m-cvrlst  li .u-cover").getLinks();
//        page.addWaitRequest(list);
//
//        List<String> nextpage = page.getHtml().$(".m-pl-pager .u-page").getLinks();
//        page.addWaitRequest(nextpage);
//
//        List<String> musics = page.getHtml().$("#m-playlist .m-table tr .f-cb").getLinks();
//        page.addWaitRequest(musics);

        Html cnt =  page.getHtml().$(".g-bd4 .cnt");

        String name = cnt.$(".hd em.f-ff2").getValue();
        List<String> singers = cnt.$("p.des").get(0).$("span a").getAll();
        String album = cnt.$("p.des").get(1).$("a").getValue();

        Html cmt = page.getHtml().$(".g-bd4 .n-cmt");
        String comment_num = cmt.$(".u-title .j-flag").getValue();
        List<String> comments = cmt.$(".m-cmmt .cmmts .cntwrap .cnt").getAll();
        page.setResult("name",name);
        page.setResult("singers",singers);
        page.setResult("album",album);
        page.setResult("comment_num",comment_num);
        page.setResult("comment",comments);





    }

    @Override
    public Setting getSetting() {
        setting = Setting.create().setStartUrl("http://music.163.com/song?id=21317959");
        setting.setDomain("http://music.163.com");
        setting.setHttpMethod("GET");
        setting.setCookies("TOKEN", "3soOOnSdtUGWNM0b");
        setting.setThreadSleep(1000);
        setting.setThreadNum(3);
        return setting;
    }

    public static void main(String[] args) {

        Bee.create(new Music163Demo())
                .setHandler(new ConsoleHandler())
                .run();

    }
}

