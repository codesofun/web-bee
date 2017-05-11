package org.bee.webBee;

import java.util.*;

/**
 * @author wangtonghe
 * @date 2017/5/3 21:48
 */
public class BeeResult {

    private Map<String, Object> result = new HashMap<>();

    private Set<String> fileResult = new HashSet<>();

    public void setFileResult(List<String> fileUrls){
        fileResult.addAll(fileUrls);
    }

    public <T>void put(String key, T value) {
        if (value != null) {
            result.put(key, value);
        }
    }

    public <T>T get(String key) {
        return (T)result.get(key);
    }


    public Map<String,Object> getResult(){
        return result;
    }

    public Set<String> getFileResults(){
        return fileResult;

    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BeeResult{");
        sb.append("result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
