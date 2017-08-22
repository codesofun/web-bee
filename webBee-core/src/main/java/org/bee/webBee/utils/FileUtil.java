package org.bee.webBee.utils;

/**
 * data 2017-05-11   01:35
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public final class FileUtil {

    /**
     * 默认下载线程数
     */
    private static int DEFAULT_DOWNLOAD_THREAD_NUM = 20;


    /**
     * 一次读取默认字节
     */
    private static byte step[] = new byte[4096];

    /**
     * 文件下载
     *
     * @param originUrl 文件源路径
     * @param destPath  文件下载目录
     */
    private static void download(String originUrl, String destPath) {


        String fileName = originUrl.substring(originUrl.lastIndexOf("/") + 1);
        if (StringUtils.isBlank(fileName)) {
            return;
        }
        try {
            URL destUrl = new URL(originUrl);
            URLConnection urlConnection = destUrl.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            OutputStream outputStream = null;
            if (destPath.endsWith("/")) {
                destPath = destPath.substring(0, destPath.length() - 1);
            }

            byte[] buffer = new byte[1024];
            int readNum = 0;

            String destFile = destPath + File.separator + fileName;
            outputStream = new FileOutputStream(destFile);

            while ((readNum = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readNum);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 批量下载文件
     *
     * @param fileUrls  文件路径集合
     * @param path      文件目录
     * @param threadNum 线程数
     */
    public static void batchDownloadFile(Set<String> fileUrls, String path, int threadNum) {
        File destDir = new File(path);
        if (!destDir.isDirectory() || !destDir.exists()) {
            System.err.println("下载目录不存在");
            return;
        }
        threadNum = threadNum > 0 ? threadNum : DEFAULT_DOWNLOAD_THREAD_NUM;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        fileUrls.forEach((url -> {
            executorService.execute(() -> FileUtil.download(url, path));
        }));
        //TODO 等爬虫线程停止后再停止，或至少等待一段时间再停止
        executorService.shutdown();
    }

    /**
     * 批量下载文件
     *
     * @param fileUrls 文件路径集合
     * @param path     文件目录
     */
    public static void batchDownloadFile(Set<String> fileUrls, String path) {
        batchDownloadFile(fileUrls, path, DEFAULT_DOWNLOAD_THREAD_NUM);
    }


    /**
     * 将json格式字符串写入指定路径的文件中
     *
     * @param json json字符串
     * @param path 待写入文件路径
     * @param name 文件名
     */
    public static void write2Json(String json, String path, String name) throws IOException {
        File pathFile = new File(path);
        if (!pathFile.isDirectory()) {
            throw new FileNotFoundException();
        }
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        if (name == null) {
            name = getDefaultName();
        }
        File file = new File(path + "/" + name);

        FileWriter writer = new FileWriter(file, true);
        if (file.length() == 0) {
            writer.append("[");
        }
        writer.append(json + ",");
        writer.close();
    }

    public static void end4Json(String path, String name) throws IOException {
        File file = new File(path + "/" + name);
        if (!file.exists()) {
            return;
        }
        FileWriter writer = new FileWriter(file, true);
        writer.append("]");
        writer.close();
    }


    private static String getDefaultName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date()) + ".json";
    }

    public static  synchronized void saveFile(CloseableHttpResponse response, String path, String fileName) {

        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path + fileName + HttpUtil.getMimeType(response)));
            InputStream inputStream = response.getEntity().getContent();
            Double streamLength = (double) response.getEntity().getContentLength();
            Long readStreamLength = 1L;
            int counts;
            //use ProgressBar plug-in  : https://github.com/ctongfei/progressbar
            ProgressBar pb = new ProgressBar(fileName,Math.round(streamLength), ProgressBarStyle.ASCII).start(); // name, initial max    ProgressBarStyle.ASCII

            while ((counts = inputStream.read(step)) != -1) {
                outputStream.write(step, 0, counts);
                readStreamLength += counts;
                pb.stepBy(counts);
                pb.setExtraMessage("下载中...");
//                System.out.print("\r " + fileName + "已经下载%" + Math.floor((readStreamLength / streamLength) * 100));
            }
            pb.stop(); // stops the progress bar
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
