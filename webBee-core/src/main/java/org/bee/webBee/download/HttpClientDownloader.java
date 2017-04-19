package org.bee.webBee.download;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.bee.webBee.HttpClient.HttpClientPool;
import org.bee.webBee.html.Html;
import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Setting;
import org.bee.webBee.processor.Task;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * data 2017-03-23   23:43
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class HttpClientDownloader implements DownLoader {

    private Logger logger = Logger.getLogger(String.valueOf(getClass()));
    private Page page = new Page();
    @Override
    public Page download(Request request, Task task) {
        Setting setting = task.getSetting();
        HttpRequestBase httpMethod =  null;
        httpMethod = HttpClientPool.getInstance().generateHttpMethod(request,task,httpMethod);

        HttpClientBuilder httpClientBuilder = HttpClientPool.getInstance().generateClient(setting,httpMethod);
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpMethod);
            page.setHtml(closeableHttpResponse);
            task.setHtml(page.getHtml()); //自传递api到Bee处理器
            closeableHttpResponse.close();
            //todo  do while策略处理异常
            return page;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    public Html getHtml(){
        return page.getHtml();
    }
}
