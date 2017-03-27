package bee_core.download;

import bee_core.HttpClient.HttpClientPool;
import bee_core.linker.Page;
import bee_core.linker.Request;
import bee_core.processor.Setting;
import bee_core.processor.Task;
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

    @Override
    public Page download(Request request, Task task) {
        Page page = new Page();
        Setting setting = task.getSetting();
        HttpClientBuilder httpClientBuilder = HttpClientPool.getInstance().generateClient(setting);
        HttpGet httpGet = new HttpGet(request.getUrl());
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
            page.setHtml(closeableHttpResponse);
            closeableHttpResponse.close();
            //todo  do while策略处理异常
            return page;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }
}
