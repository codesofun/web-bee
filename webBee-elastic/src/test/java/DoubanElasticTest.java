import org.apache.commons.lang3.StringUtils;
import org.bee.webBee.Bee;
import org.bee.webBee.handler.ConsoleHandler;
import org.bee.webBee.html.Html;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;
import webbee.handler.ElasticHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/8 09:53
 */
public class DoubanElasticTest implements PageProcessor{

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {
        List<String> tabs= page.getHtml().$(".article table.tagCol").get(0).getLinks();
        page.addWaitRequest(tabs);

        List<String> subjects = page.getHtml().$("#wrapper #content .article .item .pl2").getLinks();
        page.addWaitRequest(subjects);

        List<String> next = page.getHtml().$(".paginator").getLinks();
        page.addWaitRequest(next);

        String name = page.getHtml().$("#content h1 span[property=v:itemreviewed]").getValue();

        Html info = page.getHtml().$("#info");

        String author =info.get(0).$("span .attrs a").getValue();
        List<String> actor_list = info.$("span.actor .attrs a").getAll();
        actor_list=actor_list.size()==0?null:actor_list;

        List<String> category_list = info.$("span[property=v:genre]").getAll();
        category_list =category_list.size()==0?null:category_list;

        String country = info.$("span.pl:contains(制片国家/地区)").get(0).nextNodeText();
        String lang = info.$("span.pl:contains(语言)").get(0).nextNodeText();
        String date = info.$("span[property=v:initialReleaseDate]").get(0).getValue();

        String markText = page.getHtml().$("#interest_sectl .rating_num").getValue();

        Double mark =null;


        if(StringUtils.isNotBlank(markText)){
            mark = Double.parseDouble(markText);
        }
        Date dateTime = null;

        try {
            if(date!=null&&date.indexOf("(")>0){
                date = date.substring(0,date.indexOf("("));
                dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        page.setResult("name",name);
        page.setResult("author",author);
        page.setResult("category",category_list);
        page.setResult("actor",actor_list);
        page.setResult("lang",lang);
        page.setResult("country",country);
        page.setResult("date",dateTime);
        page.setResult("mark",mark);

    }

    @Override
    public Setting getSetting() {
        setting=Setting.create().setStartUrl("https://movie.douban.com/tag");
        setting.setDomain("https://movie.douban.com");
        setting.setHttpMethod("GET");
        setting.setCookies("bid","PI0P2w4aMDA");
        setting.setThreadSleep(2000);
        setting.setThreadNum(3);
        return setting;
    }


    public static void main(String[] args) {
        Bee.create(new DoubanElasticTest())
                .setHandler(new ElasticHandler("bee3","movie"))
                .setHandler(new ConsoleHandler())
                .run2();
    }
}
