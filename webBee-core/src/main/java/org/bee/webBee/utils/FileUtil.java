package org.bee.webBee.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangtonghe
 * @date 2017/5/5 09:35
 */
public class FileUtil {

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
        if(name==null){
            name = getDefaultName();
        }
        File file = new File(path + "/" + name);

        FileWriter writer = new FileWriter(file, true);
        if(file.length()==0){
            writer.append("[");
        }
        writer.append(json+",");
        writer.close();
    }

    public static void end4Json(String path,String name) throws IOException{
        File file = new File(path+"/"+name);
        if(!file.exists()){
            return;
        }
        FileWriter writer = new FileWriter(file,true);
        writer.append("]");
        writer.close();
    }


    private static String getDefaultName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date()) + ".json";
    }
}
