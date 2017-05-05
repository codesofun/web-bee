package org.bee.webBee.handler;

import org.bee.webBee.BeeConstant;
import org.bee.webBee.BeeResult;

import java.util.Map;

/**
 * 将结果输出到json文件中
 * @author wangtonghe
 * @date 2017/5/5 09:08
 */
public class JsonFileHandler implements Handler{

    private String path;

    public JsonFileHandler() {
       setPath(getClass().getResource("/").getFile()+ BeeConstant.DOWNLOAD_BASE_PATH);
    }

    public JsonFileHandler(String path) {
        setPath(path);
    }



    private void setPath(String path) {
        this.path = path;
    }

    @Override
    public void handle(BeeResult beeResult) {
        Map<String,String> result = beeResult.getResult();




    }
}
