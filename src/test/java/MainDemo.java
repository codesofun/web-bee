/**
 * Created by zhuang on 2017/3/23.
 */

import bee.Bee;
import bee.linker.Page;
import bee.processor.PageProcessor;
import bee.processor.Setting;

/**
 * data 2017-03-23   01:19
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class MainDemo implements PageProcessor{

    private Setting setting;
    @Override
    public void process(Page page) {
        System.out.println("This is MainDemo's process function ...");
    }

    @Override
    public Setting setting() {
        System.out.println("This is MainDemo's setting function ...");
        return setting;
    }

    public static void main(String[] args) {
        Bee.create(new MainDemo()).run();
    }
}
