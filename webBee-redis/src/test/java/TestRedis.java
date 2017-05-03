/**
 * Created by zhuang on 2017/4/22.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bee.webBee.Bee;
import org.bee.webBee.DataStoreBase;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;
import redis.clients.jedis.Jedis;
import webbee.redis.Redis;
import webbee.redis.RedisHash;
import webbee.redis.RedisSet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * data 2017-04-22   00:27
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class TestRedis implements PageProcessor {

    private Setting setting;

    private Integer count = 0;

    private RedisSet redisHash = new RedisSet();

    @Override
    public void process(Page page) throws IOException {
//        String json = page.getHtml().$("textarea.content").as("content").$("a.question_link").as("title").toJSONString();
        String api = page.getApi();

        System.out.println(count + " : ---api result:");
//        System.out.println(api);
        System.out.println(JSON.parse(api));
        count++;
//        List<Map<String, String>> list = ((List<Map<String, String>>) ((JSONObject) JSON.parse(api)).get("data"));
//        for (int i = 0; i < list.size(); i++) {
//            Map<String,String> mmap = list.get(i);
//            for (Map.Entry<String, String> entry : mmap.entrySet()) {
//                if(entry.getValue().contentEquals()){mmap.put(entry.getKey(),"true");}
//                if(entry.getValue().equals(false)){mmap.put(entry.getKey(),"false");}

//                mmap.put(entry.getKey(), entry.getValue());
//            }
//                String a;
//            }

//            String nametoken = list.get(i).get("url_token");
//            System.out.println("list"+list.get(i).toString());
//            RedisHash.create().insert("zhihu_user_" + nametoken, list.get(i));

//            redisHash.insert("zhihu_user_yet",mmap.toString());
//            Long sadd = Redis.create().sadd("zhihu_user_yet",mmap.toString());
//            if(sadd.equals(0L))

        }


//        String string = JSON.parse(api).toString();
//        System.out.println(list);
//        for(int i=0;i<list.size();i++){
//            Map<String,String> map = list.get(i);
//            for (Map.Entry<String, String> entry : map.entrySet()) {
////                if(entry.getValue()instanceof Boolean){}
//                map.put(entry.getKey(),entry.getValue().toString());
//                String a;
//            }
//        }


//        RedisHash.create().insert("zhihu_user=" + data.get("name"), data);


    @Override
    public Setting getSetting() {
        System.out.println("This is example.MainDemoByUrl's setting function ...");
//        setting = Setting.create().setStartUrl("https://www.zhihu.com/api/v4/members/koubixu/followers?include=data%5B*%5D.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=0&limit=20");
        setting = Setting.create().setStartUrl("https://www.zhihu.com/api/v4/members/wangnuonuo/followers?include=data%5B*%5D.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=157740&limit=20");
//        setting = setting.setCookies("z_c0", "Mi4wQUFEQUZwSzEzZ2tBY01ERVdCM0lDUmNBQUFCaEFsVk5WQjcyV0FEb0RnYlI3QnFQSWtzSWMzTjRHQVN0YlNlTW1R");
//        setting = setting.setCookies("_xsrf", "8e8eedb720402d12bce9b5e611837b6d");
        setting = setting.setDomain("zhihu.com");
        setting = setting.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        setting = setting.addHeader("Accept-Encoding", "gzip, deflate, sdch, br");
        setting = setting.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
        setting = setting.addHeader("Cache-Control", "max-age=0");
        setting = setting.addHeader("Connection", "keep-alive");
        setting = setting.addHeader("Host", "www.zhihu.com");
        setting = setting.addHeader("Upgrade-Insecure-Requests", "1");
        setting = setting.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        setting = setting.addHeader("Cookie", "q_c1=6061d5105e7144e9986c696caa21bb08|1492701579000|1492701579000; d_c0=\"AHBCTk4QowuPTs0xoWv4_K0tdVn73ZvN2EI=|1492701580\"; _zap=9632bb9d-c70d-40c1-9f1b-3bd23a1116ca; _xsrf=84fd0f50121c90a47808bc8a52fe09cf; aliyungf_tc=AQAAAAIT4Seu3wAARFaO29TCpRTMzi6c; acw_tc=AQAAADU0owqZ7AAARFaO2+au0E3bBCyU; r_cap_id=\"MTRlNWMwMTE0NjUzNDk0NDgyNWFmMzYyZDBhMTAyMDA=|1493649056|e5b22c0f8eafa58dffa8627435475b70dd1e3f6e\"; cap_id=\"ZGQwNTg0ZWE1NWFiNDdmNWI0MTAwMmRmYzEyM2JiYTc=|1493649056|cb88f7ea3ca27f56a3ea8e3f196e7d46f99be41f\"; l_n_c=1; __utma=51854390.295719061.1493649059.1493649059.1493649059.1; __utmb=51854390.0.10.1493649059; __utmc=51854390; __utmz=51854390.1493649059.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=51854390.100--|2=registration_date=20170416=1^3=entry_date=20170416=1; z_c0=Mi4wQUJEQ0JOcEVuUXNBY0VKT1RoQ2pDeGNBQUFCaEFsVk42Tk11V1FEdHFfQTJ4Y2x1b1l4YjBJOUM2bkJuazlaU0dn|1493649142|03a43609dde278023b95db605f4917afecb59ebb");
        setting = setting.setHttpMethod("GET");
        setting = setting.setNextUrlKeyOnResult("paging->next");
        setting = setting.setThreadSleep(1000);
//        setting = setting.setNextUrlOnCustom("offset=?&&limit=?");
        return setting;
    }


    public static void main(String[] args) {
        Bee.create(new TestRedis()).run();
    }
}
