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

    private Task task ;

    public FileDownloader(Request request,Task task){
        this.request = request;
        this.task = task;
    }

    public void downFile(String path , String fileName){
        CloseableHttpResponse response = HttpResponse.getInstance(request,task).getResponse();
        File xml = new File(path+fileName+".mp4");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(xml);
            InputStream inputStream = response.getEntity().getContent();
            byte buff[] = new byte[4096];
            int counts;
//            int count = 0;
            System.out.println("正在下载 : " + fileName );
            while ((counts = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, counts);
            }
            outputStream.flush();
            outputStream.close();
            System.out.println(fileName + "下载完成!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
