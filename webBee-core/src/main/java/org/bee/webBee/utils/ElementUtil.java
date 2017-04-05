package org.bee.webBee.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * data 2017-04-05   23:44
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class ElementUtil {

    /**
     * 根据elements元素生成list结果集
     * @param elements
     * @return
     */
    public static List<String>  elementsToList(Elements elements){
        List<String> elementsList = new ArrayList<String>();
        for(Element element : elements){
            elementsList.add(element.toString());
        }
        return elementsList;
    }
}
