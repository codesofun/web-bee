package webbee.redis;

import java.util.Map;

/**
 * data 2017-04-22   00:19
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class RedisHashUtil {

    public static void set(String key, Map<String,String> hash){
        Redis.create().hmset(key,hash);

    }
}
