package webbee.redis;


import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * data 2017-04-22   00:19
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class RedisHashUtil {
    private static Jedis jedis = new Jedis("127.0.0.1");
    public static void set(String key, Map<String,String> hash){
        jedis.hmset(key,hash);

    }
}
