package bee.download;

import bee.Bee;
import bee.linker.Page;
import bee.linker.Request;
import bee.processor.Task;

import java.util.logging.Logger;

/**
 * data 2017-03-23   23:43
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class HttpClientDownloader implements DownLoader{
    private Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Override
    public Page download(Request request, Task task) {
        logger.info("this is LOG4J ~!");
        Page page = new Page();
        return page;
    }
}
