package bee_core.linker;


import bee_core.html.Html;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.List;

/**
 * data 2017-03-20   23:10
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Page {

    private Request request;

    private List<Request> waitRequests;

    private Result result;

    private Html html;



    public void addWaitRequest(List<String> requests){
        //TODO: synchronized  单线程?
        for (String s : requests) {
            if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                break;
            }
//            s = UrlUtils.canonicalizeUrl(s, url.toString());
            waitRequests.add(new Request(s));
        }
    }

    public void setHtml(CloseableHttpResponse closeableHttpClient) {
        this.html =  new Html().getDocument(closeableHttpClient);
    }

    public Html getHtml(){
        return this.html;
    }
}
