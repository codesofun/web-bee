package downFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.media.jfxmedia.control.VideoDataBuffer;
import org.bee.webBee.Bee;
import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;
import org.omg.CORBA.Object;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * data 2017-05-11   00:03
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class TestDownVideo implements PageProcessor {

    private Setting setting;

    @Override
    public void process(Page page) throws IOException {
//        String json = page.getHtml().$("textarea.content").as("content").$("a.question_link").as("title").toJSONString();
        String api = page.getApi();
        System.out.println( JSON.parse(api) );
        String course_name = ((JSONObject) ((JSONObject) JSON.parse(api)).get("data")).get("course_name").toString();
        JSONArray video = JSON.parseArray( ((JSONObject) ((JSONObject) JSON.parse(api)).get("data")).get("video_list").toString());
//        video.forEach((final java.lang.Object obj) -> System.out.println(JSON.parseObject(obj.toString()).get("video_url")));
        for(java.lang.Object v : video){
            String video_url = JSON.parseObject(v.toString()).get("video_url").toString();
            String video_name = JSON.parseObject(v.toString()).get("video_name").toString();
            System.out.println(video_name + " : " + video_url);
        }
    }

    @Override
    public Setting getSetting() {
        System.out.println("This is example.MainDemoByUrl's setting function ...");
        setting = Setting.create().setStartUrl("http://api.maiziedu.com/v2/getCoursePlayInfo/?courseId=1&client=android");
        setting = setting.setHttpMethod("GET");
//        setting = setting.setNextUrlKeyOnResult("paging->next");
        return setting;
    }

//    File xml = new File("/Users/pg/Desktop/123.mp4");
//    FileOutputStream outputStream = new FileOutputStream(xml);
//
//    InputStream inputStream = closeableHttpResponse.getEntity().getContent();
//
//    byte buff[] = new byte[4096];
//    int counts = 0;
//    while ((counts = inputStream.read(buff)) != -1) {
//        System.out.println(".......");
//        outputStream.write(buff, 0, counts);
//
//    }
//    outputStream.flush();
//    outputStream.close();


    public static void main(String[] args) {
        Bee.create(new TestDownVideo()).run();
    }

}
