package org.bee.webBee.html;

import java.util.List;

/**
 * TODO 待重构选择器类结构
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
     * 将jsoup解析的json作为value并自定义key
     * @param selector
     * @return
     */
    Selector as(String selector);

    /**
     * 正则选择器
     *
     * @param regex
     * @return
     */
    Selector regex(String regex);

    /**
     * 获取指定索引位置元素
     *
     * @param index
     * @return
     */
    Selector get(int index);

    /**
     * 获取元素值
     *
     * @return 元素值
     */
    String getValue();


    /**
     * 获取选择元素所有值
     *
     * @return 元素列表值
     */
    List<String> getAll();


    /**
     * 获取所有a链接
     * @return
     */
    List<String> getLinks();

    /**
     * 获取所有图片url
     * @return
     */
    List<String> getImgUrls();

    /**
     * 获取所有视频url
     * @return
     */
    List<String> getVideoUrls();



    String nextNodeText();

    String prevNodeText();

}
