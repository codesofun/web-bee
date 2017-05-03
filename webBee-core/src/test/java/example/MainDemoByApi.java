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
        setting = setting.addHeader("Cookie","aliyungf_tc=AQAAAOeQ+1+bMQcAuTj1eyWWHEWGOFd5; acw_tc=AQAAALOd4Xn1TwcAuTj1e01cA+S2FFI9; q_c1=6061d5105e7144e9986c696caa21bb08|1492701579000|1492701579000; r_cap_id=\"NjBhNDM0ZmQzYTEyNGMxNWIxNDA1MjAwYmUwMDQzODY=|1492701579|48d9e12537fe7f83ad3bcaf9ed9b50c7fc995db2\"; cap_id=\"MDAxNWRlZjA5ZTRmNGNiOWE4OWZjMjBhYmRhZTUwMzg=|1492701579|49943d3f3bd8f8d52c70264684674bb22645946d\"; _xsrf=880dce32ffb2deb6467caadd8299d352; d_c0=\"AHBCTk4QowuPTs0xoWv4_K0tdVn73ZvN2EI=|1492701580\"; _zap=9632bb9d-c70d-40c1-9f1b-3bd23a1116ca; l_n_c=1; __utma=51854390.1545696888.1492701581.1492701581.1492701581.1; __utmb=51854390.0.10.1492701581; __utmc=51854390; __utmz=51854390.1492701581.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=51854390.100--|2=registration_date=20170416=1^3=entry_date=20170416=1; s-q=%E7%8E%8B%E8%AF%BA%E8%AF%BA; s-i=3; sid=0j69lkf8; s-t=autocomplete; z_c0=Mi4wQUJEQ0JOcEVuUXNBY0VKT1RoQ2pDeGNBQUFCaEFsVk43VjhnV1FCLUVaUDM1NWhXemRMalIwWXNhdE4wZi1tanJn|1492703100|d381cbe3d541399a2b1518a256b9d5a5935f9087");
        setting = setting.setHttpMethod("GET");
        setting = setting.setNextUrlKeyOnResult("paging->next");
//        setting = setting.setNextUrlOnCustom("offset=?&&limit=?");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new MainDemoByApi()).run();
    }
}
