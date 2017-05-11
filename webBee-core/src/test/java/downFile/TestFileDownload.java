package downFile;

import org.bee.webBee.utils.FileUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangtonghe
 * @date 2017/5/11 23:14
 */
public class TestFileDownload {

    @Test
    public void test()throws Exception{
        String path = "https://68.media.tumblr.com/9e3e6b6d6ac27d1bbfb490dc879a6c05/tumblr_mpzdiyv9rw1rwe56eo1_1280.jpg";
        String fileName = path.substring(path.lastIndexOf("/")+1);
        System.out.println(fileName);

        String destPath = "/usr/local/img/";
        System.out.println(destPath.substring(0,destPath.length()-1));
        Set<String> paths =new HashSet<>();
        paths.add(path);

        FileUtil.batchDownloadFile(paths,
                "/Users/wangtonghe/workspace/data/java/javaBee");


    }
}
