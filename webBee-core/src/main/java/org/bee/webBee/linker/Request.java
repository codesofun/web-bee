package org.bee.webBee.linker;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * data 2017-03-20   23:15
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 *
 *
 * 封装将要抓取的请求信息
 */
public class Request {

    private String url;

    private Object[] message;

    /**
     * cookie
     */
    private Map<String, String> cookies = new LinkedHashMap<>();

    /**
     * header
     */
    private Map<String, String> headers = new LinkedHashMap<>();

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * 构造函数
     * @param url      带抓取url
     * @param message  上下文参数
     */
    public  Request(String url,Object... message){
        this.url = url;
        this.message = message;
    }



    public String getUrl(){
        return this.url;
    }
}
