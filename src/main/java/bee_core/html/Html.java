package bee_core.html;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * data 2017-03-26   01:07
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class Html implements Selector,HtmlParser  {

    private Document document;

    private Elements elements;

    public Html(){

    }

    public Html (Document document){
        this.document = document;
    }

    public Html (Document document , Elements elements){
        this.document = document;
        this.elements = elements;
    }

    @Override
    public Html getDocument(CloseableHttpResponse httpResponse)   {
        try {
            Document document = Jsoup.parse(EntityUtils.toString(httpResponse.getEntity()));
            return new Html(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Html(document);
    }

    @Override
    public Html $(String selector) {
        return new Html(this.document,document.select(selector));
    }

    public Elements all(){
        return this.elements;
    }




}
