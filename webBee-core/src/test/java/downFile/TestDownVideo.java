package downFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bee.webBee.Bee;
import org.bee.webBee.download.FileDownloader;
import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;
import org.bee.webBee.thread.BeeExecutorPool;

import java.io.IOException;
import java.util.Scanner;

/**
 * data 2017-05-11   00:03
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class TestDownVideo implements PageProcessor {

    @Override
    public void process(Page page) throws IOException {
        BeeExecutorPool beeThreadPool = new BeeExecutorPool(5);
        String api = page.getApi();
        String course_name = ((JSONObject) ((JSONObject) JSON.parse(api)).get("data")).get("course_name").toString();
        JSONArray video = JSON.parseArray( ((JSONObject) ((JSONObject) JSON.parse(api)).get("data")).get("video_list").toString());
        for(java.lang.Object v : video){
//            beeThreadPool.execute(() -> {
                String video_url = JSON.parseObject(v.toString()).get("video_url").toString();
                String video_name = JSON.parseObject(v.toString()).get("video_name").toString();
                System.out.println(video_name + " : " + video_url);
//                new FileDownloader(new Request(video_url), page.getTask()).downFileProcessor("/Users/zhuang/Desktop/", video_name);
                page.addWaitUrl(video_url);
//            });
        }
    }

    @Override
    public Setting getSetting() {
        System.out.print("请输入课程id：");
        Scanner scanner = new Scanner(System.in);
        int courseId = scanner.nextInt();
        Setting setting = Setting.create().setStartUrl("http://api.maiziedu.com/v2/getCoursePlayInfo/?courseId=" + courseId + "&client=android");
        setting = setting.setHttpMethod("GET");
        return setting;
    }



    public static void main(String[] args) {
        Bee.create(new TestDownVideo()).run();
    }

}
