package org.bee.webBee.download;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.bee.webBee.HttpClient.HttpResponse;
import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Task;

import java.io.IOException;
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
