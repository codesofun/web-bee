package org.bee.webBee.html;

import java.util.List;

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

    /**
     * 获取指定索引位置元素
     * @param index
     * @return
     */
    Selector get(int index);

    /**
     * 获取元素值
     * @return 元素值
     */
    String getValue();


    /**
     * 获取选择元素所有值
     * @return 元素列表值
     */
    List<String> getAll();


    /**
     * 获取标签
     * @return
     */
    List<String> getLinks();

}
