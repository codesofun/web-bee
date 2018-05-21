package example; /**
 * Created by zhuang on 2017/3/23.
 */



import com.alibaba.fastjson.JSON;
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

    private Integer count = 0;
    @Override
    public void process(Page page) throws IOException {
//        String json = page.getHtml().$("textarea.content").as("content").$("a.question_link").as("title").toJSONString();
        String api = page.getApi();

        System.out.println(count+" : ---api result:");
//        System.out.println(api);
         System.out.println( JSON.parse(api) );
        count++;
    }

    @Override
    public Setting getSetting() {
        System.out.println("This is example.MainDemoByUrl's setting function ...");
        setting = Setting.create().setStartUrl("https://www.zhihu.com/api/v4/members/koubixu/followers?include=data%5B*%5D.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=0&limit=20");
//        setting = Setting.create().setStartUrl("https://www.zhihu.com/api/v4/members/wangnuonuo/followers?include=data%5B*%5D.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=0&limit=20");

        setting = setting.setDomain("zhihu.com");
        setting = setting.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        setting = setting.addHeader("Accept-Encoding","gzip, deflate, sdch, br");
        setting = setting.addHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
        setting = setting.addHeader("Cache-Control","max-age=0");
        setting = setting.addHeader("Connection","keep-alive");
        setting = setting.addHeader("Host","zhihu.com");
        setting = setting.addHeader("Upgrade-Insecure-Requests","1");
        setting = setting.addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        setting = setting.addHeader("Cookie","d_c0=\"AHBCTk4QowuPTs0xoWv4_K0tdVn73ZvN2EI=|1492701580\"; _zap=9632bb9d-c70d-40c1-9f1b-3bd23a1116ca; q_c1=6061d5105e7144e9986c696caa21bb08|1506316115000|1492701579000; __DAYU_PP=mvU2ZM7RnfJeqjIYBJna2911b246d4a8; __utma=155987696.350837047.1524803761.1524803761.1524803761.1; __utmz=155987696.1524803761.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; q_c1=6061d5105e7144e9986c696caa21bb08|1525321980000|1492701579000; _xsrf=5348aed3-5d4e-404b-812e-88552e539882; tgw_l7_route=56f3b730f2eb8b75242a8095a22206f8; capsion_ticket=\"2|1:0|10:1526919135|14:capsion_ticket|44:MTM3NWY5MjFkM2YwNDAzNjljMDgxODM1ZWZhMGRlYTY=|42990fb74a71d0bbb63bb01972fcf014dca09c8efaad0eb163d024817ab21a73\"; z_c0=\"2|1:0|10:1526919283|4:z_c0|92:Mi4xUC1DVUNRQUFBQUFBY0VKT1RoQ2pDeVlBQUFCZ0FsVk5jejd3V3dEU0IzVVNJWWN4LWstb2ltUjhOZ0lmdURSVkNB|036748deda83a8431bb417b446b24dbe37a41f2bec6cab489397dcbe04fc354e\"");
        setting = setting.setHttpMethod("GET");
        //你需要解析的json数据格式 data->paging->next
        setting = setting.setNextUrlKeyOnResult("paging->next");
//        setting = setting.setNextUrlOnCustom("offset=?&&limit=?");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new MainDemoByApi()).run();
    }
}
