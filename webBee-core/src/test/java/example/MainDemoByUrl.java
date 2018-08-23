package example; /**
 * Created by zhuang on 2017/3/23.
 */



import org.bee.webBee.Bee;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;


/**
 * 类似servlet 实现HttpServlet doGet doPost 方法的方式定义爬虫
 * data 2017-03-23   01:19
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */

public class MainDemoByUrl implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) {
        //todo page.getJson/html/string().$('textarea.content').as('content').bulid().$('#img').as('img')
        //todo 期望结果: {content:[],img:[]} 一条{}多条[] 的json格式
        //todo page.nextUrl('span>ss>s')
        //todo 直接获取api接口
        String json = page.getHtml().$("textarea.content").as("content").$("a.question_link").as("title").toJSONString();
//
        System.out.println(json);
    }

    @Override
    public Setting getSetting() {
        System.out.println("This is example.MainDemoByUrl's setting function ...");
        setting = Setting.create().setStartUrl("http://www.ZhiHu.com/explore");
        //添加cookie,模拟登陆  也可以选择setting.addCookie(key,value)添加cookie;
//        setting = setting.addHeader("Cookie","d_c0=\"AHBCTk4QowuPTs0xoWv4_K0tdVn73ZvN2EI=|1492701580\"; _zap=9632bb9d-c70d-40c1-9f1b-3bd23a1116ca; q_c1=6061d5105e7144e9986c696caa21bb08|1506316115000|1492701579000; __DAYU_PP=mvU2ZM7RnfJeqjIYBJna2911b246d4a8; __utma=155987696.350837047.1524803761.1524803761.1524803761.1; __utmz=155987696.1524803761.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; q_c1=6061d5105e7144e9986c696caa21bb08|1525321980000|1492701579000; _xsrf=5348aed3-5d4e-404b-812e-88552e539882; tgw_l7_route=56f3b730f2eb8b75242a8095a22206f8; capsion_ticket=\"2|1:0|10:1526919135|14:capsion_ticket|44:MTM3NWY5MjFkM2YwNDAzNjljMDgxODM1ZWZhMGRlYTY=|42990fb74a71d0bbb63bb01972fcf014dca09c8efaad0eb163d024817ab21a73\"; z_c0=\"2|1:0|10:1526919283|4:z_c0|92:Mi4xUC1DVUNRQUFBQUFBY0VKT1RoQ2pDeVlBQUFCZ0FsVk5jejd3V3dEU0IzVVNJWWN4LWstb2ltUjhOZ0lmdURSVkNB|036748deda83a8431bb417b446b24dbe37a41f2bec6cab489397dcbe04fc354e\"");
//        setting = setting.setDomain("zhihu.com");
//        setting = setting.setHttpMethod("GET");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new MainDemoByUrl()).run();
    }
}
