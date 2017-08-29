package org.bee.webBee.html;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.bee.webBee.utils.ElementUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;


/**
 * data 2017-03-26   01:07
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class Html implements Selector, HtmlParser {

    private String document;

    private Elements elements;

    private Elements allElements;

    private Element element;

    private Map<String, List<String>> elementsMap = new HashMap();

    private CloseableHttpResponse closeableHttpClient;

    public Html() {


    }

    public Html(String document) {
        this.document = document;
        this.allElements = Jsoup.parse(this.document).getAllElements();
    }

    public Html(CloseableHttpResponse closeableHttpClient) {
        this.closeableHttpClient = closeableHttpClient;
    }

    public Html(String document, Elements elements , Elements allElements,  Map<String, List<String>> elementsMap) {
        this.document = document;
        this.elements = elements;
        this.allElements = allElements;
        this.elementsMap = elementsMap;
    }
    private Html(String document,Element element){
        this.document = document;
        this.element = element;
    }

    public Html(String document, Elements allElements, Map<String, List<String>> elementsMap) {
        this.document = document;
        this.allElements = allElements;
        this.elementsMap = elementsMap;
    }



    public Html getHtml() {
        try {
//            Document document = Jsoup.parse(EntityUtils.toString(httpResponse.getEntity()));
            String html = EntityUtils.toString(this.closeableHttpClient.getEntity());
            return new Html(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Html("");
    }

    public String getStringApi() {
        return document;
    }

    public JSONObject getJsonApi() {
        JSONObject json = null;
        try {
            json = (JSONObject) JSON.parse(document);
        } catch (Exception e) {
            System.out.println("Json 转换格式错误!");
            System.out.println("异常栈:"+e);
        }
        return json;
    }

    @Override
    public Html $(String selector) {
        if(this.document!=null){
            if(allElements!=null) {
                return new Html(this.document, this.allElements.select(selector) ,this.allElements,this.elementsMap);
            }else if(element!=null){
                return new Html(this.document,element.select(selector),this.allElements,this.elementsMap);
            }else{
                return new Html();
            }
        }
        return new Html();

    }

    //TODO 待实现
    @Override
    public Selector regex(String regex) {
        return null;

    }

    @Override
    public Selector get(int index) {
        if(allElements!=null&&allElements.size()>=index+1){
            return new Html(document,allElements.get(index));
        }
        return new Html();

    }

    /**
     * 将jsoup解析的json作为value并自定义key
     * @param key
     * @return
     */
    public Html as(String key) {
        this.elementsMap.put(key, ElementUtil.elementsToList(this.elements));
        return new Html(this.document, this.allElements, this.elementsMap);
    }

    public List<String> getLinks(String selector) {
        this.allElements = Jsoup.parse(document).select(selector).select("a");
        List<String> list = new ArrayList<String>();
        for (Element element : allElements) {
            list.add(element.attr("href"));
        }
        return list;
    }

    @Override
    public List<String> getLinks() {

        if(allElements==null&&element==null){
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();

        if(allElements==null){
            allElements = element.select("a");
            for (Element element : allElements) {
                list.add(element.attr("href"));
            }
        }else{
            elements = elements.select("a");
            for (Element element : elements) {
                list.add(element.attr("href"));
            }
        }

        return list;
    }

    @Override
    public List<String> getImgUrls() {
        return getUrls("img");
    }

    @Override
    public List<String> getVideoUrls() {
        return getUrls("video");
    }


    private List<String> getUrls(String type) {
        if(allElements==null&&element==null){
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();

        if(allElements==null){
            allElements = element.select(type);
            for (Element element : allElements) {
                list.add(element.attr("src"));
            }
        }else{
            allElements = allElements.select(type);
            for (Element element : allElements) {
                list.add(element.attr("src"));
            }
        }

        return list;
    }


    public Map<String, List<String>> getResult() {
        return elementsMap;
    }


    public String toJSONString() {
        return JSON.toJSONString(this.elementsMap);
    }

    public Object toJSON() {
        return JSON.parse(JSON.toJSONString(this.elementsMap));
    }


    @Override
    public String getDocument() {
        return document;
    }

    @Override
    public String getValue() {
        if (element == null&&allElements==null) {
            return null;
        }
        if(element==null){
            return allElements.size()>0?allElements.get(0).text():null;
        }
        return element.text();
    }

    @Override
    public List<String> getAll() {
        if (allElements == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        allElements.forEach((e) -> list.add(e.text()));
        return list;
    }

    @Override
    public String nextNodeText(){
        if(element==null){
            return null;
        }
        return element.nextSibling().toString();
    }

    @Override
    public String prevNodeText(){
        if(element==null){
            return null;
        }
        return element.previousSibling().toString();
    }

}
