/**
 * Created by zhuang on 2017/4/22.
 */

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import webbee.redis.RedisHashUtil;

import java.util.Map;
import java.util.Set;

/**
 * data 2017-04-22   00:27
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class TestHash {
    public static void main(String[] args) {
        Map<String,String> map = (Map<String, String>) JSON.parse("{\"is_org\":\"false\",\"gender\":\"1\",\"is_followed\":\"false\"," +
                "\"url_token\":\"baimo\",\"type\":\"people\",\"answer_count\":\"205\"," +
                "\"follower_count\":\"71512\",\"url\":\"http://www.zhihu.com/api/v4/people/0dddf50c5ddb7d5194407e4a69f31f48\"," +
                "\"badge\":\"[]\",\"user_type\":\"people\",\"avatar_url\":\"https://pic4.zhimg.com/eb7442cd6913420418390112f2d1610f_is.jpg\"," +
                "\"is_advertiser\":\"false\",\"articles_count\":\"6\",\"name\":\"白墨\",\"id\":\"0dddf50c5ddb7d5194407e4a69f31f48\"," +
                "\"avatar_url_template\":\"https://pic4.zhimg.com/eb7442cd6913420418390112f2d1610f_{size}.jpg\"," +
                "\"headline\":\"Catch me if you can.   公众号：WeBallsohard  \",\"is_following\":\"false\"}");

        System.out.println(map.get("name"));
        RedisHashUtil.set(map.get("name"),map);

        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("asdd"+jedis.sadd("asdd","asddd"));
        Set set = jedis.keys("白*");
//        jedis.hgetAll("bookname");
        System.out.println("set : "+set);
    }
}
