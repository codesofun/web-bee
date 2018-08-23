package org.bee.webBee.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Setting;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.bee.webBee.processor.Task;

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

    public HttpClientBuilder getClient(Setting setting,HttpRequestBase httpMethod){
        return generateClient(setting,httpMethod);
    }
    /**
     * 根据配置自动生成需要的HTTP配置
     * @param setting
     * @return
     */
    public HttpClientBuilder generateClient(Setting setting, HttpRequestBase httpMethod){
        RequestConfig requestConfig = RequestConfig.custom().build();
        HttpClientBuilder httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig);
        generateHeaders(httpMethod,setting);
        generateCookies(httpClient,setting);
        return httpClient;
    }

    /**
     * 根据配置自动生成需要的 header
     * @param httpMethod
     * @param setting
     */
    private void generateHeaders(HttpRequestBase httpMethod, Setting setting) {
        for(Map.Entry<String,String> headerEntry : setting.getHeader().entrySet()){
            httpMethod.setHeader(headerEntry.getKey(),headerEntry.getValue());
        }
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

    public HttpRequestBase generateHttpMethod(Request request,Task task , HttpRequestBase httpRequestBase ){
        if( task.getSetting().getHttpMethod() == null || task.getSetting().getHttpMethod().equals("GET") ) {
            httpRequestBase = new HttpGet(request.getUrl());
            this.setHttpConfig(httpRequestBase);
            return  httpRequestBase;
        }
        return  httpRequestBase;
    }

    /**
     * 设置响应超时时间
     */
    public void setHttpConfig(HttpRequestBase httpMethod){
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        httpMethod.setConfig(requestConfig);
    }
}
