package downFile;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtonghe
 * @date 2017/5/13 00:12
 */
public class TestFileType {

    private   Map<String,String> fileTypeMap = new HashMap<>();



    @Before
    public void before(){
        fileTypeMap.put("ffd8ff", "jpg");
        fileTypeMap.put("89504e47", "png");
        fileTypeMap.put("47494638", "gif");
        fileTypeMap.put("68746d6c3e", "html");
        fileTypeMap.put("6d6f6f76","mov");
        fileTypeMap.put("57415645","wav");
        fileTypeMap.put("00000020667479706d70","mp4");
        fileTypeMap.put("49443303000000002176","mp3");
    }

    @Test
    public void test(){

        try {
            File file = new File("/Users/wangtonghe/Desktop/402H.jpg");
            FileInputStream inputStream = new FileInputStream(file);
            try {
                byte[] b = new byte[28];
                inputStream.read(b, 0, 28);
                String fileHead = bytesToHexString(b);
                if(StringUtils.isBlank(fileHead)) {
                    return;
                }

                fileTypeMap.forEach((key,value)->{
                    if(fileHead.startsWith(key)){
                        System.out.println(value);
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private  String bytesToHexString(byte[] src) {

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
