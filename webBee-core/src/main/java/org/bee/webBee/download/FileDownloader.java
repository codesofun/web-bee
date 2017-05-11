package org.bee.webBee.download;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.bee.webBee.HttpClient.HttpResponse;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Task;
import org.bee.webBee.utils.FileUtil;

import java.io.*;

/**
 * data 2017-05-11   01:35
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class FileDownloader {

    private Request request;

    private Task task;

    public FileDownloader(Request request, Task task) {
        this.request = request;
        this.task = task;
    }

    /**
     * 下载文件
     * @param path
     * @param fileName
     */
    public void downFileProcessor(String path, String fileName) {
        CloseableHttpResponse response = HttpResponse.getInstance(request, task).getResponse();
        FileUtil.saveFile(response,path,fileName);
    }
}
