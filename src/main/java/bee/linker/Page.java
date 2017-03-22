package bee.linker;


import bee.html.Selector;
import org.apache.commons.lang3.StringUtils;

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

    private Selector html;

    public Selector getHtml() {
        return html;
    }

    public void addWaitRequest(List<String> requests){
        for (String s : requests) {
            if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                break;
            }
//            s = UrlUtils.canonicalizeUrl(s, url.toString());
            waitRequests.add(new Request(s));
        }
    }
}
