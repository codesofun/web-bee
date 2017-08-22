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
        setting = setting.addHeader("Host","www.zhihu.com");
        setting = setting.addHeader("Upgrade-Insecure-Requests","1");
        setting = setting.addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        setting = setting.addHeader("Cookie","d_c0=\"AHBCTk4QowuPTs0xoWv4_K0tdVn73ZvN2EI=|1492701580\"; _zap=9632bb9d-c70d-40c1-9f1b-3bd23a1116ca; q_c1=6061d5105e7144e9986c696caa21bb08|1500789237000|1492701579000; q_c1=6061d5105e7144e9986c696caa21bb08|1500789237000|1492701579000; aliyungf_tc=AQAAAKuBthrGBQkAOiAvcSpAeDQcAU+4; r_cap_id=\"ZGE2ZDIzYWRhMDA0NDM0MDgxNzI4M2ZmN2U2ODc0ZDk=|1503375703|f27506bb96ac61f5dc4eee2e6b221be5711d82db\"; cap_id=\"MmVkMzczNzg4ZjE1NDYxMTljNTg2ODk4YjliMDdiMWY=|1503375703|6f093dae6733f3d24a38941b76b0c604d1757318\"; __utma=51854390.728883040.1503302344.1503302344.1503375703.2; __utmb=51854390.0.10.1503375703; __utmc=51854390; __utmz=51854390.1503375703.2.2.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __utmv=51854390.000--|2=registration_date=20160504=1^3=entry_date=20170420=1; l_cap_id=\"MDRhYmNjOTI0Zjg3NDRlZjk1OWJhNDg0YWE5ZWFmZmQ=|1503375748|35ca7670e26e9aab906ea34b3f790164397aa543\"; z_c0=Mi4xWVBMREJRQUFBQUFBY0VKT1RoQ2pDeGNBQUFCaEFsVk5tajdEV1FEYnVFYVBWRmdrSFNMLURKV25LblhRQ0d2TEhn|1503375770|eabf91031457017a63bacaf49356d85485986971; unlock_ticket=\"QUpCQ19Cc2ZRZ3dYQUFBQVlRSlZUYUs0bTFrYjdxbndjamtadFJrMWFyVFFxNHRwWFctTThRPT0=|1503375770|bdb7d52666c0238036a4a7e2536c46528e294dd1\"; _xsrf=3c0570c7-fc62-47bd-a2fb-613eb2457e31");
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
