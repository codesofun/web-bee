package org.bee.webBee.html;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ParameterMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 * data 2017-03-26   01:07
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class Html implements Selector,HtmlParser  {

    private Document document;

    private Elements elements;

    private Map<String,String> elementsMap = new HashMap<String, String>();

    public Html(){

    }

    public Html (Document document){
        this.document = document;
    }

    public Html (Document document , Elements elements){
        this.document = document;
        this.elements = elements;
    }

    public Html (Document document , Elements elements ,Map<String,String> elementsMap){
        this.document = document;
        this.elements = elements;
        this.elementsMap = elementsMap;
    }

    @Override
    public Html getDocument(CloseableHttpResponse httpResponse)   {
        try {
            Document document = Jsoup.parse(EntityUtils.toString(httpResponse.getEntity()));
            return new Html(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Html(document);
    }

    @Override
    public Html $(String selector) {
        this.elements = document.select(selector);
        return this;
//        return new Html(this.document,document.select(selector));
//        return as("s")
    }

    public Html as (String key){
        this.elementsMap.put(key,this.elements.toString());
        return new Html(this.document,this.elements,this.elementsMap);
    }

    public String toJSONString(){
        return JSON.toJSONString(this.elementsMap);
    }




}
