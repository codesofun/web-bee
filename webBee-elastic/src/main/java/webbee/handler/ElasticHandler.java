package webbee.handler;

import org.bee.webBee.BeeResult;
import org.bee.webBee.handler.Handler;
import webbee.elasticsearch.ESHelper;

import java.net.UnknownHostException;

/**
 * @author wangtonghe
 * @date 2017/5/8 08:51
 */
public class ElasticHandler implements Handler{


    private String index;

    private String type;

    private ESHelper esHelper;


    //TODO 从配置文件中获取 或传值？
    private String clusterName = "elasticsearch";

    private String host = "127.0.0.1";

    private int port = 9300;



    public ElasticHandler(String index,String type) {

        try {
            this.esHelper = new ESHelper(clusterName,host,port);
            this.index = index;
            this.type = type;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void handle(BeeResult beeResult) {

        esHelper.insert(index,type,beeResult.getResult());

    }

    @Override
    public void destory() {


    }

    @Override
    public void setDomain(String domain) {

    }
}
