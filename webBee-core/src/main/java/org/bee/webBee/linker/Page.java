package org.bee.webBee.linker;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.bee.webBee.html.Html;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.bee.webBee.processor.Setting;

import java.io.IOException;
import java.util.List;

/**
 * data 2017-03-20   23:10
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Page {

    private Request request;

    private List<Request> waitRequests;

    private Result result;

    private Html html;

    private CloseableHttpResponse closeableHttpClient;

    private String api;



    public void addWaitRequest(List<String> requests){
        //TODO: synchronized  同步锁
        for (String s : requests) {
            if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                break;
            }
//            s = UrlUtils.canonicalizeUrl(s, url.toString());
            waitRequests.add(new Request(s));
        }
    }

    public void setHtml(CloseableHttpResponse closeableHttpClient) throws IOException {
        this.html =  new Html(closeableHttpClient).getDocument();
        this.api = this.html.getStringApi();
        this.closeableHttpClient = closeableHttpClient;
//        this.api = EntityUtils.toString(closeableHttpClient.getEntity());
    }

    public Html getHtml(){
        return this.html;
    }

    public  String getApi()   {

        return api;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
