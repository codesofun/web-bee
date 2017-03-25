package bee_core.HttpClient;

import bee_core.processor.Setting;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.Map;

/**
 * 封裝HTTPCLIENT 连接池
 *
 * data 2017-03-25   16:14
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class HttpClientPool {

    public static HttpClientPool getInstance(){
        return new HttpClientPool();
    }

    public HttpClientBuilder getClient(Setting setting){
        return generateClient(setting);
    }
    /**
     * 根据配置自动生成需要的HTTP配置
     * @param setting
     * @return
     */
    public HttpClientBuilder generateClient(Setting setting){
        RequestConfig requestConfig = RequestConfig.custom().build();
        HttpClientBuilder httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig);

        generateCookies(httpClient,setting);
        return httpClient;
    }

    /**
     * 根据配置自动生成需要的Cookies
     * @param httpClient
     * @param setting
     */
    public void generateCookies(HttpClientBuilder httpClient, Setting setting){
        CookieStore cookieStore = new BasicCookieStore();
        for(Map.Entry<String,String> cookieEntry : setting.getCookies().entrySet()){
            BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
            cookie.setDomain(setting.getDomain());
            cookie.setPath("/");
            cookieStore.addCookie(cookie);
             httpClient.setDefaultCookieStore(cookieStore).build();
        }
    }
}
