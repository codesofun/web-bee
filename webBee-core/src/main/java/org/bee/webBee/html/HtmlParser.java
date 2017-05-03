package org.bee.webBee.html;

import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * Created by zhuang on 2017/3/25.
 */
public interface HtmlParser {

    /**
     * 获取一个文档类型
     * @param
     * @return
     */
    String getDocument();

    /**
     * 获取元素值
     * @return
     */
    String getValue();

}
