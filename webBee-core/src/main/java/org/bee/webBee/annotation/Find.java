package org.bee.webBee.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * data 2017-08-31   16:15
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Find {

}
