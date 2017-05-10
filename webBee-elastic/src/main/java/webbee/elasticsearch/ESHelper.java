package webbee.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author wangtonghe
 * @date 2017/5/8 09:03
 */
public final class ESHelper {


    private TransportClient client;


    public ESHelper(String clusterName, String host) throws UnknownHostException {

        Settings settings = Settings.builder().put("cluster.name", clusterName)
                .build();
        this.client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
    }

    public ESHelper(String clusterName, String host, int port) throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", clusterName)
                .build();
        this.client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
    }

    /**
     * 向ES中添加数据
     * @param index 索引
     * @param type 类型
     * @param param 要添加的数据，Map类型
     * @return
     */
    public boolean insert(String index,String type,Map<String,Object> param){
      return   client.prepareIndex(index,type).setSource(param).get().status().name()
              .equals(ESRestStatus.CREATED.name());

    }


}
