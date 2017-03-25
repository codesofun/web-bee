package bee_core.HttpClient;

import bee_core.processor.Setting;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.net.www.http.HttpClient;

/**
 * 封裝HTTPCLIENT 连接池
 *
 * data 2017-03-25   16:14
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class HttpClientPool {

    public void  generateClient(Setting setting){
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        HttpGet getHomePage = new HttpGet(setting.getStartUrl());
    }
}
