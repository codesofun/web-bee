package org.bee.webBee.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtonghe
 * @date 2017/5/3 21:48
 */
public class BeeResults {

    private Map<String, String> result = new HashMap<>();

    public void put(String key, String value) {
        if (value != null) {
            result.put(key, value);
        }
    }

    public String get(String key) {
        return result.get(key);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BeeResults{");
        sb.append("result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
