package org.bee.webBee.linker;


import org.bee.webBee.BeeResult;
import org.bee.webBee.html.Html;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.bee.webBee.processor.Task;
import org.bee.webBee.utils.UrlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * data 2017-03-20   23:10
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Page {

    private Request request;

    private Set<Request> waitRequests = new HashSet<>();

    private BeeResult beeResult= new BeeResult();

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


    private Task task;

    public void addWaitUrl(String url){
        //TODO: synchronized  同步锁
        waitRequests.add(new Request(url));
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

    public void setResult(String key,Object value){
        beeResult.put(key,value);
    }

    public BeeResult getBeeResults(){
        return beeResult;
    }

    public void setFileResult(List<String> fileUrls){
        beeResult.setFileResult(fileUrls);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Set<Request> getWaitRequests() {
        return waitRequests;
    }

    public void setWaitRequests(Set<Request> waitRequests) {

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

    public void setTast(Task task) {
        this.task = task;
    }

    public Task getTask(){
        return task;
    }


}
