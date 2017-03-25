package example.HttpClient.ZhiHu;
/**
 * data 2017-03-25   18:47
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */

import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;



public class LoginByCookie {

    public static void main(String[] args) throws IOException {
        login();
    }

    public static void login() throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().build();

//        HttpGet getHomePage = new HttpGet("http://www.ZhiHu.com/");

        // Create a local instance of cookie store
        CookieStore cookieStore = new BasicCookieStore();
// Populate cookies if needed
        BasicClientCookie cookie = new BasicClientCookie("z_c0", "Mi4wQUFEQUZwSzEzZ2tBY01ERVdCM0lDUmNBQUFCaEFsVk5WQjcyV0FEb0RnYlI3QnFQSWtzSWMzTjRHQVN0YlNlTW1R|1490436835|d866afdca33075ec83c944f3d7627528bb335dee");
        cookie.setDomain("zhihu.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);

        BasicClientCookie cookie2 = new BasicClientCookie("_xsrf", "8e8eedb720402d12bce9b5e611837b6d");
        cookie2.setDomain("zhihu.com");
        cookie2.setPath("/");
        cookieStore.addCookie(cookie2);
// Set the store

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setDefaultCookieStore(cookieStore).build();

        try {

            HttpGet g = new HttpGet("http://www.ZhiHu.com/explore");//获取“我关注的问题”页面
            CloseableHttpResponse r = httpClient.execute(g);

            Document doc = Jsoup.parse(EntityUtils.toString(r.getEntity()));
            Element body = doc.body();
            Elements divs = body.select("textarea.content");
            for(Element div : divs){
                System.out.println("------------------------------------------");
                System.out.println(div);
                System.out.println("------------------------------------------");
            }


            r.close();
            next(httpClient);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void next(CloseableHttpClient httpClient) throws IOException {

        HttpGet g = new HttpGet("https://www.zhihu.com");//获取“首页”页面
        CloseableHttpResponse r = httpClient.execute(g);
//        System.out.println(EntityUtils.toString(r.getEntity()));
        r.close();
    }
}