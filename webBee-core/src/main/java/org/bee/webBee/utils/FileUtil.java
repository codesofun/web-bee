package org.bee.webBee.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author wangtonghe
 * @date 2017/5/5 09:35
 */
public class FileUtil {

    /**
     * 将json格式字符串写入指定路径的文件中
     * @param json json字符串
     * @param path 待写入文件路径
     * @param name 文件名
     */
    public  static void write2Json(String json,String path,String name)throws FileNotFoundException{
        File pathFile = new File(path);
        if(!pathFile.isDirectory()){
            throw new  FileNotFoundException();
        }
        File file = new File(path+name);
        if(!file.exists()){
           file.mkdirs();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write(json);
        writer.close();
    }
}
