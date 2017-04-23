package webbee.redis;

import org.bee.webBee.DataStoreBase;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * data 2017-04-22   00:19
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class RedisSet implements DataStoreBase {
    private Jedis redis = Redis.create();

    public boolean insert(String key, String value) {
         return redis.sadd(key, value).equals(1L);
    }

    @Override
    public Object insert(Object value) {
        return null;
    }

    @Override
    public Object select() {
        return null;
    }


    public static RedisSet create(){
        return new RedisSet();
    }



}
