package org.bee.webBee.html;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.bee.webBee.utils.ElementUtil;
import org.bee.webBee.utils.JsonUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
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


    public Html getHtml()   {
        try {
//            Document document = Jsoup.parse(EntityUtils.toString(httpResponse.getEntity()));
             String html = EntityUtils.toString(this.closeableHttpClient.getEntity());
            return new Html(html);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Html("");
    }

    public String getStringApi(){
        return document;
    }

    public JSONObject getJsonApi(){
        JSONObject json = null;
        try {
            json = (JSONObject) JSON.parse(document);
        }catch (Exception e){
            System.out.println("Json 转换格式错误!");
//            System.out.println("异常栈:"+e);
        }
        return json;
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

    public List<String> getLinks(String selector){
        this.elements = Jsoup.parse(document).select(selector).select("a");
        List<String> list = new ArrayList<String>();
        for(Element element:elements){
            list.add(element.attr("href"));
        }
        return list;
    }

    public Map<String,List<String>> getResult(){
        return elementsMap;
    }



    public String toJSONString(){
        return JSON.toJSONString(this.elementsMap);
    }

    public Object toJSON(){
        return  JSON.parse(JSON.toJSONString(this.elementsMap));
    }


    @Override
    public String getDocument() {
        return document;
    }

    @Override
    public String getValue() {
        if(elements==null||elements.size()<1){
            return null;
        }
        return elements.get(0).text();
    }

    public Elements getElements(){
        return elements;
    }
}
