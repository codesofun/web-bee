package bee_core.html;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.nodes.Document;

/**
 * Created by zhuang on 2017/3/25.
 */
public interface HtmlParser {

    /**
     * 获取一个文档类型
     * @param closeableHttpResponse
     * @return
     */
    Document getDocument(CloseableHttpResponse closeableHttpResponse);

}
