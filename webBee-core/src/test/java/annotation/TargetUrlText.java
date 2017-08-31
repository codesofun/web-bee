package annotation;

import org.bee.webBee.Bee;
import org.bee.webBee.annotation.TargetUrl;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;

/**
 * data 2017-08-31   16:36
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
@TargetUrl("http://www.ZhiHu.com/explore")
public class TargetUrlText implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) {
        //todo page.getJson/html/string().$('textarea.content').as('content').bulid().$('#img').as('img')
        //todo 期望结果: {content:[],img:[]} 一条{}多条[] 的json格式
        //todo page.nextUrl('span>ss>s')
        //todo 直接获取api接口
        String json = page.getHtml().$("textarea.content").as("content").$("a.question_link").as("title").toJSONString();

        System.out.println(json);
    }

    @Override
    public Setting getSetting() {
        System.out.println("This is example.MainDemoByUrl's setting function ....");
//        setting = Setting.create().setStartUrl("http://www.ZhiHu.com/explore");
        setting = Setting.create();
//        添加cookie,模拟登陆  也可以选择setting.addCookie(key,value)添加cookie;
        setting = setting.addHeader("Cookie","d_c0=\"AHBCTk4QowuPTs0xoWv4_K0tdVn73ZvN2EI=|1492701580\"; _zap=9632bb9d-c70d-40c1-9f1b-3bd23a1116ca; q_c1=6061d5105e7144e9986c696caa21bb08|1500789237000|1492701579000; q_c1=6061d5105e7144e9986c696caa21bb08|1500789237000|1492701579000; aliyungf_tc=AQAAAKuBthrGBQkAOiAvcSpAeDQcAU+4; r_cap_id=\"ZGE2ZDIzYWRhMDA0NDM0MDgxNzI4M2ZmN2U2ODc0ZDk=|1503375703|f27506bb96ac61f5dc4eee2e6b221be5711d82db\"; cap_id=\"MmVkMzczNzg4ZjE1NDYxMTljNTg2ODk4YjliMDdiMWY=|1503375703|6f093dae6733f3d24a38941b76b0c604d1757318\"; __utma=51854390.728883040.1503302344.1503302344.1503375703.2; __utmb=51854390.0.10.1503375703; __utmc=51854390; __utmz=51854390.1503375703.2.2.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __utmv=51854390.000--|2=registration_date=20160504=1^3=entry_date=20170420=1; l_cap_id=\"MDRhYmNjOTI0Zjg3NDRlZjk1OWJhNDg0YWE5ZWFmZmQ=|1503375748|35ca7670e26e9aab906ea34b3f790164397aa543\"; z_c0=Mi4xWVBMREJRQUFBQUFBY0VKT1RoQ2pDeGNBQUFCaEFsVk5tajdEV1FEYnVFYVBWRmdrSFNMLURKV25LblhRQ0d2TEhn|1503375770|eabf91031457017a63bacaf49356d85485986971; unlock_ticket=\"QUpCQ19Cc2ZRZ3dYQUFBQVlRSlZUYUs0bTFrYjdxbndjamtadFJrMWFyVFFxNHRwWFctTThRPT0=|1503375770|bdb7d52666c0238036a4a7e2536c46528e294dd1\"; _xsrf=3c0570c7-fc62-47bd-a2fb-613eb2457e31");
        setting = setting.setDomain("zhihu.com");
        setting = setting.setHttpMethod("GET");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new TargetUrlText()).run();
    }
}
