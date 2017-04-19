/**
 * Created by zhuang on 2017/3/23.
 */



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
//        System.out.println(api);
         System.out.println( JSON.parse(api) );
    }

    @Override
    public Setting getSetting() {
        System.out.println("This is MainDemoByUrl's setting function ...");
        setting = Setting.create().setStartUrl("https://www.zhihu.com/api/v4/members/wangnuonuo/followers?include=data%5B*%5D.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=0&limit=20");
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
        setting = setting.addHeader("Cookie","d_c0=\"AHDAxFgdyAmPTogb7lNAvzbTxg28kIJWdI4=|1460828288\"; _za=734b06fa-050b-4553-b501-f5d02c611704; _zap=6e6c0aea-e6dd-435f-8e69-6996fc56c663; q_c1=4175eaaa410648709d544febff40e30d|1491108278000|1480295125000; _xsrf=6a5970e3d6d3f280436ce979ac8ee5e5; aliyungf_tc=AQAAACkm6T5mCAkA0RnzdIqC96Kb0mzM; r_cap_id=\"ZjZlNDAxYzkzMWVhNGRiNGIwNzQxN2UxNDIwZTM5YWM=|1492535054|e6d1e5f169fe7b7fb964a84893a78a3fb78b4691\"; cap_id=\"MzMwYzc1NmE4MGNhNGZkY2JkNDlkZWFjYTdlY2NiY2Q=|1492535054|0ed3b733742e2cbc44688717d5c2723891007785\"; l_n_c=1; __utma=51854390.526056812.1491981516.1492483218.1492535056.4; __utmb=51854390.0.10.1492535056; __utmc=51854390; __utmz=51854390.1492483218.3.3.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/question/58474365/answer/157552230; __utmv=51854390.100--|2=registration_date=20160504=1^3=entry_date=20160504=1; z_c0=Mi4wQUFEQUZwSzEzZ2tBY01ERVdCM0lDUmNBQUFCaEFsVk5LZFFkV1FDVUpsdDl3ZjZqUThxdjd5aHZZLVl1SkFEQnhB|1492535107|a5b78f65f79cb3d976c9643e787ff3524a86eb37");
        setting = setting.setHttpMethod("GET");
        setting = setting.setNextUrlKeyOnResult("next");
//        setting = setting.setNextUrlOnCustom("offset=?&&limit=?");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new MainDemoByApi()).run();
    }
}
