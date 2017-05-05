package org.bee.webBee.utils;


/**
 * data 2017-03-23   00:44
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class UrlUtils {
    /**
     * 进行url地址转化
     * @param url url地址
     * @param refer url地址来自哪个页面
     * @return url绝对地址
     */
    public static  String canonicalizeUrl(String url,String refer){
        if(url.startsWith("http")||url.startsWith("https")){
            return url;
        }
        if(url.startsWith("/")){
            int index = refer.indexOf("//");
            String sub = refer.substring(index + 2);
            String res = refer.substring(0, index + 2)+sub.substring(0, sub.indexOf("/"));
            return res+url;
        }
        return refer+url;

    }

}
