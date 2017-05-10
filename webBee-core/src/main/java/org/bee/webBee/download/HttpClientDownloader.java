package org.bee.webBee.download;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.bee.webBee.HttpClient.HttpClientPool;
import org.bee.webBee.HttpClient.HttpResponse;
import org.bee.webBee.html.Html;
import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Setting;
import org.bee.webBee.processor.Task;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * data 2017-03-23   23:43
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class HttpClientDownloader implements DownLoader {

    private Logger logger = Logger.getLogger(String.valueOf(getClass()));
//    private Page page = new Page();
    @Override
    public Page download(Request request, Task task) {
        CloseableHttpResponse closeableHttpResponse  = HttpResponse.getInstance(request,task).getResponse();
        Page page = new Page();
        try {
            System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
            System.out.println(request.getUrl());

            page.setHtml(closeableHttpResponse);
            page.setRequest(request);
            page.setTast(task);
            page.setUrl(request.getUrl());
            page.setStatusCode(closeableHttpResponse.getStatusLine().getStatusCode());
            task.setHtml(page.getHtml()); //自传递api到Bee处理器
            closeableHttpResponse.close();
            //todo  do while策略处理异常

            return page;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }


}
