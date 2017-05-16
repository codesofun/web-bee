package processor;

import org.junit.Test;

/**
 * @author wangtonghe
 * @date 2017/5/4 09:24
 */
public class TestUrl {

    @Test
    public void test() {

        String url = "/tag/喜剧";
        String refer = "https://movie.douban.com";

        int pos = refer.lastIndexOf("/");
        if(pos>refer.indexOf("//")+1){
            refer = refer.substring(0,pos);
            System.out.println(refer);
        }
        System.out.println(refer+url);

//        int index = refer.indexOf("//");
//        String sub = refer.substring(index + 2);
//        String res = refer.substring(0, index + 2)+sub.substring(0, sub.indexOf("/"));
//        System.out.println(res);

//        System.out.println(.substring(0,));


//        System.out.println(url.substring(0,url.indexOf()));


    }
}
