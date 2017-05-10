import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtonghe
 * @date 2017/5/7 22:25
 */
public class TestElasticsearch {

    @Test
    public void test() {

        try {
            Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                    .build();

            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//            IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
//                    .setSource(jsonBuilder()
//                            .startObject()
//                            .field("user", "kimchy")
//                            .field("postDate", new Date())
//                            .field("message", "trying out Elasticsearch")
//                            .endObject()
//                    )
//                    .get();
//            System.out.println(response);

            Map<String,Object> result = new HashMap<>();
            result.put("user","Mary6");
            result.put("postDate",new Date());
            result.put("massage","this is Mary6");

            IndexResponse response2 = client.prepareIndex("twitter","tweet")
                    .setSource(result).get();
            System.out.println(response2.status().name().equals("CREATED"));



            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
