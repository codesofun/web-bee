package example;

import org.bee.webBee.Bee;
import org.bee.webBee.handler.ConsoleHandler;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;

import java.io.IOException;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/3 08:31
 */
public class DoubanMovieDemo implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {
//        page.addWaitRequest();
        List<String> subject= page.getHtml().getLinks("#content");
        List<String> tabs = page.getHtml().getLinks(".article");
        page.addWaitRequest(subject);
        page.addWaitRequest(tabs);

        String name = page.getHtml().$("#content .span[property=v:itemreviewed]").getValue();
        String author = page.getHtml().$(".article #info .attrs").getValue();
        String mark = page.getHtml().$("#interest_sectl strong.rating_num").getValue();
        page.setResult("name",name);
        page.setResult("author",author);
        page.setResult("mark",mark);




    }

    @Override
    public Setting getSetting() {
        setting=Setting.create().setStartUrl("https://movie.douban.com/tag");
        setting.setDomain("https://movie.douban.com");
        setting.setHttpMethod("GET");
        setting.setCookies("bid","rZFPxvXrqwY");
        setting.setThreadNum(2);
        return setting;
    }

    public static void main(String[] args) {
        Bee.create(new DoubanMovieDemo()).setHandler(new ConsoleHandler()).run2();
    }
}
