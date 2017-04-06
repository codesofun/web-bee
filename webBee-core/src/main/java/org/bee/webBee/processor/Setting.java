package org.bee.webBee.processor;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 爬虫的配置类
 * data 2017-03-23   01:55
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Setting {

    private String domain;

    private String userAgent;

    private String userName;

    private String password;

    private String startUrl;

    private Map<String,String> cookies = new LinkedHashMap<String, String>();//todo Collections.synchronizedMap?

    /**
     * 个性化配置类入口返回配置对象,省去了new Setting()的步骤
     * @return
     */
    public static Setting create() {
        return new Setting();
    }
    /**
     * 设置一个作用域
     * @param domain
     * @return
     */
    public Setting setDomain(String domain) {
        this.domain = domain;
        return this ;
    }

    /**
     * 设置一个userAgent
     * @param userAgent
     * @return
     */
    public Setting setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * 设置一个登录账号(若有需要)
     * @param userName
     * @return
     */
    public Setting setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 设置一个登录密码(若有需要)
     * @param password
     * @return
     */
    public Setting setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * 获取已经设置的作用域
     * @return
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 获取已经设置的userAgent
     * @return
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 获取已经设置的账户
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 获取已经设置的密码
     * @return
     */
    public String getPassword() {
        return password;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public Setting setStartUrl (String startUrl) {
        this.startUrl = startUrl;
        return this;
    }

    public Setting setApi (String startUrl) {
        this.startUrl = startUrl;
        return this;
    }


    public Map<String, String> getCookies() {
        return cookies;
    }

    public Setting setCookies(String key, String value) {
        cookies.put(key,value);
        return this;
    }


}
