package org.bee.webBee.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

/**
 * data 2017-05-13   01:21
 * E-mail   sis.nonacosa@gmail.com
 *
 * @author sis.nonacosa
 */
public class HttpUtil {

    public static String getMimeType(CloseableHttpResponse response) {
        try {
            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
            return allTypes.forName(response.getEntity().getContentType().getValue()).getExtension();
        } catch (MimeTypeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
