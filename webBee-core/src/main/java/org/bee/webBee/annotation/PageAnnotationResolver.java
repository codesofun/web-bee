package org.bee.webBee.annotation;

import org.bee.webBee.linker.Page;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * data 2017-08-31   17:00
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class PageAnnotationResolver  {

    private Page page ;
    private List<String> targetUrls = new ArrayList<String>();
    private Class clazz ;

    public static PageAnnotationResolver create(Class clazz){
        PageAnnotationResolver pageAnnotationResolver = new PageAnnotationResolver();
        pageAnnotationResolver.init(clazz);
        return pageAnnotationResolver;
    }

    private void init(Class clazz){
        this.clazz = clazz;
        initClassResolver(clazz);
    }

    private void initClassResolver(Class clazz){
        Annotation annotation = clazz.getAnnotation(TargetUrl.class);
        if(annotation == null){
            System.out.println("targetUrl null");
        }else{
            TargetUrl targetUrl = (TargetUrl) annotation;
            String[] value = targetUrl.value();
            for (String s : value) {
                System.out.println("targetUrl value : -> " + s);
                targetUrls.add(s);
            }
        }
    }

    public List<String> getTargetUrls(){
        return targetUrls;
    }


}
