package org.bee.webBee.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.bee.webBee.HttpClient.HttpResponse;

import javax.sound.sampled.AudioInputStream;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
            OutputStream outputStream= null;
            if (destPath.endsWith("/")) {
                destPath = destPath.substring(0, destPath.length() - 1);
            }

            byte[] buffer = new byte[1024];
            int readNum = 0;

            //处理文件名
            if (!fileName.contains(".")) {
                byte[] fileHeadBuffer = new byte[28];
                int headSize = inputStream.read(fileHeadBuffer);
                String newFileName = fileName + "." + getFileSuffix(fileHeadBuffer);
                String destFile = destPath + File.separator + newFileName;
                outputStream = new FileOutputStream(destFile);
                outputStream.write(fileHeadBuffer, 0, headSize);
            }else{
                String destFile = destPath + File.separator + fileName;
                outputStream = new FileOutputStream(destFile);
            }
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

    public static void saveFile(CloseableHttpResponse response, String path, String fileName) {

        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path + fileName + HttpUtil.getMimeType(response)));
            InputStream inputStream = response.getEntity().getContent();
            Double streamLength = (double) response.getEntity().getContentLength();
            Double readStreamLength = 0.00;
            int counts;
            while ((counts = inputStream.read(step)) != -1) {
                outputStream.write(step, 0, counts);
                readStreamLength += counts;
                System.out.print("\r " + fileName + "已经下载%" + Math.floor((readStreamLength / streamLength) * 100));
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

    /**
     * 根据文件流的开始字节部分获取文件类型
     * @param headByte
     * @return
     */
    private static String getFileSuffix(byte[] headByte) {
        //TODO 暂时先存在map中
        Map<String, String> fileTypeMap = new HashMap<>();
        fileTypeMap.put("ffd8ff", "jpg");
        fileTypeMap.put("89504e47", "png");
        fileTypeMap.put("47494638", "gif");
        fileTypeMap.put("68746d6c3e", "html");
        fileTypeMap.put("6d6f6f76", "mov");
        fileTypeMap.put("57415645", "wav");
        fileTypeMap.put("00000020667479706d70", "mp4");
        fileTypeMap.put("49443303000000002176", "mp3");

        String fileHead = FileUtil.bytesToHexString(headByte);
        if (StringUtils.isBlank(fileHead)) {
            return null;
        }
        StringBuilder fileType = new StringBuilder();
        fileTypeMap.forEach((key, value) -> {
            if (fileHead.startsWith(key)) {
                fileType.append(value);
            }
        });
        return fileType.toString();
    }

    private static String bytesToHexString(byte[] src) {

        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }




}
