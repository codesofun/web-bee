package bee.download;

import bee.Bee;
import bee.linker.Page;
import bee.linker.Request;

import java.util.logging.Logger;

/**
 * data 2017-03-23   23:43
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class HttpClientDownloader implements DownLoader{
    private Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Override
    public Page download(Request request, Bee bee) {

    }
}
