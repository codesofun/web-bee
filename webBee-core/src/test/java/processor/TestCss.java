package processor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/6 09:51
 */
public class TestCss {

    @Test
    public void test() {

        String html = "<div id=\"info\">\n" +
                "        <span><span class=\"pl\">导演</span>: <span class=\"attrs\"><a href=\"/celebrity/1014891/\" rel=\"v:directedBy\">李相日</a></span></span><br>\n" +
                "        <span><span class=\"pl\">编剧</span>: <span class=\"attrs\"><a href=\"/celebrity/1314681/\">吉田修一</a> / <a href=\"/celebrity/1014891/\">李相日</a></span></span><br>\n" +
                "        <span class=\"actor\"><span class=\"pl\">主演</span>: <span class=\"attrs\"><span><a href=\"/celebrity/1027181/\" rel=\"v:starring\">渡边谦</a> / </span><span><a href=\"/celebrity/1024080/\" rel=\"v:starring\">森山未来</a> / </span><span><a href=\"/celebrity/1008067/\" rel=\"v:starring\">松山研一</a> / </span><span><a href=\"/celebrity/1314909/\" rel=\"v:starring\">绫野刚</a> / </span><span><a href=\"/celebrity/1328056/\" rel=\"v:starring\">广濑铃</a> / </span><span style=\"display: none;\"><a href=\"/celebrity/1002495/\" rel=\"v:starring\">皮埃尔泷</a> / </span><span style=\"display: none;\"><a href=\"/celebrity/1315155/\" rel=\"v:starring\">三浦贵大</a> / </span><span style=\"display: none;\"><a href=\"/celebrity/1366918/\" rel=\"v:starring\">佐久本宝</a> / </span><span style=\"display: none;\"><a href=\"/celebrity/1330848/\" rel=\"v:starring\">高畑充希</a> / </span><span style=\"display: none;\"><a href=\"/celebrity/1043383/\" rel=\"v:starring\">原日出子</a> / </span><span style=\"display: none;\"><a href=\"/celebrity/1042111/\" rel=\"v:starring\">池胁千鹤</a> / </span><span style=\"display: none;\"><a href=\"/celebrity/1037138/\" rel=\"v:starring\">宫崎葵</a> / </span><span style=\"display: none;\"><a href=\"/celebrity/1037028/\" rel=\"v:starring\">妻夫木聪</a></span><a href=\"javascript:;\" class=\"more-actor\" title=\"更多主演\">更多...</a></span></span><br>\n" +
                "        <span class=\"pl\">类型:</span> <span property=\"v:genre\">剧情</span> / <span property=\"v:genre\">爱情</span> / <span property=\"v:genre\">悬疑</span> / <span property=\"v:genre\">同性</span><br>\n" +
                "        <span class=\"pl\">官方网站:</span> <a href=\"http://ikari-movie.com\" rel=\"nofollow\" target=\"_blank\">ikari-movie.com</a><br>\n" +
                "        <span class=\"pl\">制片国家/地区:</span> 日本<br>\n" +
                "        <span class=\"pl\">语言:</span> 日语 / 英语<br>\n" +
                "        <span class=\"pl\">上映日期:</span> <span property=\"v:initialReleaseDate\" content=\"2016-09-10(多伦多电影节)\">2016-09-10(多伦多电影节)</span> / <span property=\"v:initialReleaseDate\" content=\"2016-09-17(日本)\">2016-09-17(日本)</span><br>\n" +
                "        <span class=\"pl\">片长:</span> <span property=\"v:runtime\" content=\"142\">142分钟</span><br>\n" +
                "        <span class=\"pl\">又名:</span> 愤怒 / Rage / Anger<br>\n" +
                "        <span class=\"pl\">IMDb链接:</span> <a href=\"http://www.imdb.com/title/tt4384088\" target=\"_blank\" rel=\"nofollow\">tt4384088</a><br>\n" +
                "\n" +
                "</div>";

        Element element = Jsoup.parse(html).select("#info").get(0);
//
        Element before = element.select("span.pl:contains(制片国家/地区)").get(0);
        System.out.println(before.nextSibling());
    }

    @Test
    public void test2(){
        String date = "2016-09-10(中国大陆)";
        Date  dateTime =null;
        try {
            if(date.indexOf("(")>0){
                date = date.substring(0,date.indexOf("("));
                System.out.println(date);
                dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(date);
        System.out.println(dateTime);

//        System.out.println(date.);


    }
}