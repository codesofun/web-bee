/**
 * Created by zhuang on 2017/3/23.
 */



import com.sun.media.jfxmedia.logging.Logger;
import org.bee.webBee.Bee;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;

import java.io.IOException;


/**
 * 类似servlet 实现HttpServlet doGet doPost 方法的方式定义爬虫
 * data 2017-03-23   01:19
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */

public class MainDemoByApi implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {
        //todo page.getJson/html/string().$('textarea.content').as('content').bulid().$('#img').as('img')
        //todo 期望结果: {content:[],img:[]} 一条{}多条[] 的json格式
        //todo page.nextUrl('span>ss>s')
        //todo 直接获取api接口
//        String json = page.getHtml().$("textarea.content").as("content").$("a.question_link").as("title").toJSONString();
        String api = page.getApi();

        System.out.println("api result:");
        System.out.println(api);
    }

    @Override
    public Setting getSetting() {
        System.out.println("This is MainDemoByUrl's setting function ...");
        setting = Setting.create().setStartUrl("https://www.zhihu.com/api/v4/members/wangnuonuo/followers?include=data%5B*%5D.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=340320&limit=20");
        setting = setting.setCookies("z_c0","Mi4wQUFEQUZwSzEzZ2tBY01ERVdCM0lDUmNBQUFCaEFsVk5WQjcyV0FEb0RnYlI3QnFQSWtzSWMzTjRHQVN0YlNlTW1R");
        setting = setting.setCookies("_xsrf","8e8eedb720402d12bce9b5e611837b6d");
        setting = setting.setDomain("zhihu.com");
        setting = setting.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        setting = setting.addHeader("Accept-Encoding","gzip, deflate, sdch, br");
        setting = setting.addHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
        setting = setting.addHeader("Cache-Control","max-age=0");
        setting = setting.addHeader("Connection","keep-alive");
        setting = setting.addHeader("Host","www.zhihu.com");
        setting = setting.addHeader("Upgrade-Insecure-Requests","1");
        setting = setting.addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        setting = setting.addHeader("Cookie","gsScrollPos=; d_c0=\"AHDAxFgdyAmPTogb7lNAvzbTxg28kIJWdI4=|1460828288\"; _za=734b06fa-050b-4553-b501-f5d02c611704; _zap=6e6c0aea-e6dd-435f-8e69-6996fc56c663; _xsrf=8e8eedb720402d12bce9b5e611837b6d; r_cap_id=\"ODJlN2UxMjAzNWYxNDZiY2JkMjhjY2NjZjFjYTMxY2I=|1489932624|515289b6475b3473d1a29d47d75822d1d977dfa7\"; cap_id=\"MDc4Njc3NmEzMjUxNDIwNzllZWI1YWE2YmU1NzdmMjk=|1489932624|861229cb79a29148e3e65fe8a96a656e8bc02398\"; nweb_qa=heifetz; q_c1=4175eaaa410648709d544febff40e30d|1491108278000|1480295125000; aliyungf_tc=AQAAACmAVjdOmAYA0RnzdLh1i2ReUxHn; s-q=%E9%9B%84%E5%8E%BF; s-i=6; sid=cd2ms89o; s-t=autocomplete; gsScrollPos=; z_c0=Mi4wQUFEQUZwSzEzZ2tBY01ERVdCM0lDUmNBQUFCaEFsVk5WQjcyV0FEb0RnYlI3QnFQSWtzSWMzTjRHQVN0YlNlTW1R|1491451954|f11996f8031c49aa3f506ab2de953e81089d625e; __utma=155987696.1268385530.1491452403.1491452403.1491452403.1; __utmb=155987696.0.10.1491452403; __utmc=155987696; __utmz=155987696.1491452403.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
        setting = setting.setHttpMethod("GET");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new MainDemoByApi()).run();
    }
}
