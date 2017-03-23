package bee.processor;

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

    public static Setting create() {
        return new Setting();
    }
}
