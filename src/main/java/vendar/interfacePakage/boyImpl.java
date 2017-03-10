package vendar.interfacePakage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuang on 2017/3/7.
 * 如何模拟框架实现接口时指针指向当前调用类 实现时有默认数据
 */
public class boyImpl implements BoyInterFace {

    public void findGirl(List girls) {
        System.out.println(girls);
    }

    public static void main(String[] args) {
        Finding find  = new Finding(new boyImpl());


    }
}
