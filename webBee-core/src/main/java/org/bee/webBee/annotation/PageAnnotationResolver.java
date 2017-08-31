package org.bee.webBee.annotation;

import org.bee.webBee.linker.Page;

/**
 * data 2017-08-31   17:00
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class PageAnnotationResolver {

    private Class clazz ;

    public static PageAnnotationResolver create(Class clazz){
        PageAnnotationResolver pageAnnotationResolver = new PageAnnotationResolver();
        pageAnnotationResolver.init(clazz);
        return pageAnnotationResolver;
    }

    public void init(Class clazz){
        this.clazz = clazz;
    }
}
