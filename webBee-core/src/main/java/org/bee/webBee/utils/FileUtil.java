package org.bee.webBee.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangtonghe
 * @date 2017/5/5 09:35
 */
public final class FileUtil {

    /**
     * 默认下载线程数
     */
    private static int DEFAULT_DOWNLOAD_THREAD_NUM = 20;

    private static String HTTP_PREFIX = "http:";

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
        originUrl = doOriginUrl(originUrl);
        if(StringUtils.isBlank(originUrl)){
            return;
        }

        try {
            URL destUrl = new URL(originUrl);
            URLConnection urlConnection = destUrl.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            if (destPath.endsWith("/")) {
                destPath = destPath.substring(0, destPath.length() - 1);
            }
            String newFile = destPath + File.separator + fileName;
            FileOutputStream outputStream = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int readNum = 0;
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
     * @throws FileNotFoundException
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
        executorService.shutdown();
    }

    /**
     * 批量下载文件
     *
     * @param fileUrls 文件路径集合
     * @param path     文件目录
     * @throws FileNotFoundException
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

    /**
     * 处理源路径
     * @param originUrl
     * @return
     */
    private static String doOriginUrl(String originUrl){
        if(originUrl.startsWith("http")){
            return originUrl;
        }else if(originUrl.startsWith("//")){
            return HTTP_PREFIX+originUrl;
        }
        return null;


    }
}
