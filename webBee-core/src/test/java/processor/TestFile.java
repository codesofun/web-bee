package processor;

import org.bee.webBee.BeeConstant;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author wangtonghe
 * @date 2017/5/5 09:46
 */
public class TestFile {

    @Test
    public void test()throws Exception{

        String path = getClass().getResource("/").getFile()+ BeeConstant.DOWNLOAD_BASE_PATH;

        File pathFile = new File(path);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        String fileUrl = path+"a.json";
        System.out.println(fileUrl);
        File  file = new File(fileUrl);
        PrintWriter  writer = new PrintWriter(file);
        writer.write("{\"name\":\"wthfeng\"}");
        writer.close();


    }

    @Test
    public void testDate(){
        System.out.println(new Date().getTime());
    }
}
