package example;

import org.bee.webBee.Bee;
import org.bee.webBee.handler.ConsoleHandler;
import org.bee.webBee.handler.JsonFileHandler;
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

        List<String> tabs= page.getHtml().$(".article table.tagCol").get(0).getLinks();
        page.addWaitRequest(tabs);

        List<String> subjects = page.getHtml().$("#wrapper #content .article .item .pl2").getLinks();
        page.addWaitRequest(subjects);

//        List<String> next = page.getHtml().$(".paginator").getLinks();
//        page.addWaitRequest(next);

        String name = page.getHtml().$("#content h1 span[property=v:itemreviewed]").get(0).getValue();

        String author = page.getHtml().$("#info span").get(0).$(".attrs a").getValue();
        List<String> actor_list = page.getHtml().$("#info span.actor .attrs a").getAll();
        actor_list=actor_list.size()==0?null:actor_list;

        List<String> category_list = page.getHtml().$("#info span[property=v:genre]").getAll();
        category_list =category_list.size()==0?null:category_list;

//        String country = page.getHtml().$("#info>span").get(6).getValue();
        String date = page.getHtml().$("#info span[property=v:initialReleaseDate]").get(0).getValue();

        String mark = page.getHtml().$("#interest_sectl .rating_num").get(0).getValue();

        page.setResult("name",name);
        page.setResult("author",author);
        page.setResult("actor",actor_list);
        page.setResult("category",category_list);
        page.setResult("date",date);
        page.setResult("mark",mark);


    }

    @Override
    public Setting getSetting() {
        setting=Setting.create().setStartUrl("https://movie.douban.com/tag");
        setting.setDomain("https://movie.douban.com");
        setting.setHttpMethod("GET");
        setting.setCookies("bid","PI0P2w4aMDI");
        setting.setThreadSleep(1000);
        setting.setThreadNum(2);
        return setting;
    }

    public static void main(String[] args) {
        Bee.create(new DoubanMovieDemo())
                .setHandler(new JsonFileHandler("/Users/wangtonghe/workspace/data/java/javaBee"))
                .setHandler(new ConsoleHandler())
                .run2();
    }
}
