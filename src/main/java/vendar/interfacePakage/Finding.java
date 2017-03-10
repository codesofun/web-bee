package vendar.interfacePakage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuang on 2017/3/7.
 */
public class Finding {
    private List girls = new ArrayList();
    private BoyInterFace boyInterFace;



    public  Finding(BoyInterFace boy){
        this.boyInterFace = boy;
        this.girls.add("first girl");
        this.girls.add("second girl");
//        boyInterFace = new boyImpl();
        List a = new ArrayList();
        a.add("123456789");
        boyInterFace.findGirl(a);
    }

    public List getGirls(){
        return this.girls;
    }
}
