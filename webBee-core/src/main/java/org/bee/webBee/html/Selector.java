package org.bee.webBee.html;

/**
 * selector html Document list with css selector.
 * <p>
 * data 2017-03-21   01:52
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public interface Selector {

    /**
     * 使用类似Jquery $ 选择器
     *
     * @param selector
     * @return
     */
    Selector $(String selector);

    /**
     * 正则选择器
     * @param regex
     * @return
     */
    Selector regex(String regex);





}
