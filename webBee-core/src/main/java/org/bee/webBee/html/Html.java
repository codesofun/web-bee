package org.bee.webBee.html;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.bee.webBee.utils.ElementUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ParameterMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data 2017-03-26   01:07
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class Html implements Selector,HtmlParser  {

    private String  document;

    private Elements elements;

    private Map<String,List<String>> elementsMap = new HashMap<String, List<String>>() ;

    private CloseableHttpResponse closeableHttpClient;

    public Html(){

    }

    public Html (String document){
        this.document = document;
    }

    public Html ( CloseableHttpResponse closeableHttpClient){
        this.closeableHttpClient = closeableHttpClient;
    }

    public Html (String document , Elements elements){
        this.document = document;
        this.elements = elements;
    }

    public Html (String document , Elements elements ,Map<String,List<String>> elementsMap){
        this.document = document;
        this.elements = elements;
        this.elementsMap = elementsMap;
    }

    @Override
    public Html getDocument()   {
        try {
//            Document document = Jsoup.parse(EntityUtils.toString(httpResponse.getEntity()));
             String html = EntityUtils.toString(this.closeableHttpClient.getEntity());
            return new Html(html);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Html("");
    }

    public String getApi(){
        return document;
    }

    @Override
    public Html $(String selector) {
        this.elements =  Jsoup.parse(document).select(selector);
        return this;
//        return new Html(this.document,document.select(selector));
//        return as("s")
    }

    public Html as (String key){
        this.elementsMap.put(key, ElementUtil.elementsToList(this.elements));
        return new Html(this.document,this.elements,this.elementsMap);
    }

    public String toJSONString(){
        return JSON.toJSONString(this.elementsMap);
    }

    public Object toJSON(){
        return  JSON.parse(JSON.toJSONString(this.elementsMap));
    }




}
