package org.bee.webBee.handler;

import org.bee.webBee.BeeResult;
import org.bee.webBee.utils.FileUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 文件下载处理器
 *
 * @author wangtonghe
 * @date 2017/5/11 22:19
 */
public class FileDownloadHandler implements Handler {

    private String path;

    private static String HTTP_PREFIX = "http:";

    private String domain;


    public FileDownloadHandler(String path) {
        this.path = path;
    }

    @Override
    public void handle(BeeResult beeResult) {
        Set<String> originUrls = beeResult.getFileResults();

        Set<String> newUrls = new HashSet<>();
        originUrls.forEach(url -> newUrls.add(doOriginUrl(url,domain)));

        FileUtil.batchDownloadFile(newUrls, path);

    }

    @Override
    public void destory() {

    }

    @Override
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 处理源路径
     *
     * @param originUrl
     * @return
     */
    private static String doOriginUrl(String originUrl,String domain) {
        if (originUrl.startsWith("http")) {
            return originUrl;
        } else if (originUrl.startsWith("//")) {
            return HTTP_PREFIX + originUrl;
        }else if(originUrl.startsWith("/")){
            return domain+originUrl;
        }
        return null;


    }

}
