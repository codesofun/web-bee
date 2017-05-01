package org.bee.webBee.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * data 2017-04-01    17:42
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class JsonUtil {

    public static String jsonCustomKey (JSONObject json, String s) {
        if(json != null && s != null){
            JSONObject temp = json;
            String[] customArr = s.split("->");
            for(int i=0;i<customArr.length;i++){
                if(i == customArr.length-1){
                    return temp.get(customArr[i]).toString();
                }else {
                    temp = (JSONObject) temp.get(customArr[i]);
                }
            }
        }
        return null;
    }
}
