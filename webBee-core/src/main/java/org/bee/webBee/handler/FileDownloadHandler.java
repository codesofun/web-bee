package org.bee.webBee.handler;

import org.bee.webBee.BeeResult;
import org.bee.webBee.utils.FileUtil;

import java.util.Set;

/**
 * 文件下载处理器
 * @author wangtonghe
 * @date 2017/5/11 22:19
 */
public class FileDownloadHandler implements Handler {

    private String path;

    public FileDownloadHandler(String path) {
        this.path = path;
    }

    @Override
    public void handle(BeeResult beeResult) {
        Set<String> urls = beeResult.getFileResults();
        FileUtil.batchDownloadFile(urls,path);

    }

    @Override
    public void destory() {

    }
}
