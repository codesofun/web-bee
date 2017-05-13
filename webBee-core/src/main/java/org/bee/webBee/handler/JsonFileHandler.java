package org.bee.webBee.handler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.bee.webBee.BeeConstant;
import org.bee.webBee.BeeResult;
import org.bee.webBee.utils.FileUtil;

import java.io.IOException;
import java.util.Map;

/**
 * 将结果输出到json文件中
 * @author wangtonghe
 * @date 2017/5/5 09:08
 */
public class JsonFileHandler implements Handler{


    private String path;

    private String name;

    public JsonFileHandler() {
       setPath(getClass().getResource("/").getFile()+ BeeConstant.DOWNLOAD_BASE_PATH,null);
    }

    public JsonFileHandler(String path) {
        setPath(path,null);
    }

    public JsonFileHandler(String path,String name){
        setPath(path,name);
    }


    private void setPath(String path,String name) {

        this.path = path;
        this.name = name;
    }

    @Override
    public void handle(BeeResult beeResult) {
        Map<String,Object> result = beeResult.getResult();
        if(result==null||result.size()==0){
            return;
        }
        try {
            String jsonResult = JSON.toJSONString(result);
            if(StringUtils.isNotBlank(jsonResult)) {
                FileUtil.write2Json(jsonResult, path,name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destory() {

        try {
            FileUtil.end4Json(path,name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setDomain(String domain) {

    }
}
