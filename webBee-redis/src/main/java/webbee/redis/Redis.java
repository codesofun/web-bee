package webbee.redis;

import redis.clients.jedis.Jedis;

/**
 * data 2017-04-22   01:47
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Redis {

    public static Jedis create(){
        return new Jedis("127.0.0.1");
    }
}
