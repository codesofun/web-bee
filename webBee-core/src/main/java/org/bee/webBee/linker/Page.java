package org.bee.webBee.linker;


import org.bee.webBee.handler.BeeResults;
import org.bee.webBee.html.Html;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.bee.webBee.utils.UrlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * data 2017-03-20   23:10
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Page {

    private Request request;

    private List<Request> waitRequests = new ArrayList<>();

    private BeeResults beeResults= new BeeResults();

    private Result result;

    private Html html;

    private CloseableHttpResponse closeableHttpClient;

    private String api;

    private String domain;



    /**
     * 正在处理页的url

     */
    private String url;



    private int statusCode;



    public void addWaitRequest(List<String> requests){
        //TODO: synchronized  同步锁
        for (String s : requests) {
            if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                break;
            }
            s = UrlUtils.canonicalizeUrl(s, getUrl());
            waitRequests.add(new Request(s));
        }
    }

    public void setHtml(CloseableHttpResponse closeableHttpClient) throws IOException {
        this.html =  new Html(closeableHttpClient).getHtml();
        this.api = this.html.getStringApi();
        this.closeableHttpClient = closeableHttpClient;
//        this.api = EntityUtils.toString(closeableHttpClient.getEntity());
    }

    public Html getHtml(){
        return this.html;
    }

//    public

    public  String getApi()   {

        return api;
    }

    public void setResult(String key,String value){
        beeResults.put(key,value);
    }

    public BeeResults getBeeResults(){
        return beeResults;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<Request> getWaitRequests() {
        return waitRequests;
    }

    public void setWaitRequests(List<Request> waitRequests) {

        this.waitRequests = waitRequests;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
