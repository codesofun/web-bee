package example;

import org.bee.webBee.Bee;
import org.bee.webBee.handler.ConsoleHandler;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;

import java.io.IOException;
import java.util.List;

/**
 * 豆瓣电影爬虫demo
 * @author wangtonghe
 * @date 2017/5/3 08:31
 */
public class DoubanMovieDemo implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {
//        page.addWaitRequest();
        List<String> subject= page.getHtml().$(".article table.tagCol").getLinks();
        page.addWaitRequest(subject);

        String name = page.getHtml().$(".article .item .pl2 a").getValue();
        String mark = page.getHtml().$(".article .item span.rating_nums").getValue();
        List<String> next = page.getHtml().$(".paginator").getLinks();
        page.addWaitRequest(next);
        page.setResult("name",name);
        page.setResult("mark",mark);
    }

    @Override
    public Setting getSetting() {
        setting=Setting.create().setStartUrl("https://movie.douban.com/tag");
        setting.setDomain("https://movie.douban.com");
        setting.setHttpMethod("GET");
        setting.setCookies("bid","PI0P2w4aMDI");
        setting.setThreadSleep(3000);
        setting.setThreadNum(1);
        return setting;
    }

    public static void main(String[] args) {
        Bee.create(new DoubanMovieDemo()).setHandler(new ConsoleHandler()).run2();
    }
}
