package org.bee.webBee.download;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.bee.webBee.HttpClient.HttpResponse;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Task;

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

    public void downFile(String path, String fileName) {
        CloseableHttpResponse response = HttpResponse.getInstance(request, task).getResponse();
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path + fileName + ".mp4"));
            InputStream inputStream = response.getEntity().getContent();
            Double streamLength = (double) response.getEntity().getContentLength();
            Double readStreamLength = 0.00;
            byte buff[] = new byte[4096];
            int counts;
            System.out.println("正在下载 : " + fileName);
            while ((counts = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, counts);
                readStreamLength += counts;
                System.out.print("\r 已经下载%" + Math.floor((readStreamLength / streamLength) * 100) );
            }

            outputStream.flush();
            outputStream.close();
            System.out.print(fileName + "下载完成!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
