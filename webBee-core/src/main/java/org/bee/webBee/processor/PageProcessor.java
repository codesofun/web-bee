package org.bee.webBee.processor;

import org.bee.webBee.linker.Page;

import java.io.IOException;

/**
 * 页面处理
 *
 * data 2017-03-23   01:10
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public interface PageProcessor {

    /**
     * 自定义:页面处理页面,链接提取,内容提取等规则
     */
    void process(Page page) throws IOException;

    /**
     * 自定义开启前配置项,cookie 起始页 抓取时间 超时时间
     */
    Setting getSetting();
}
