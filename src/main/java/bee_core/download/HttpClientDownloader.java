package bee_core.download;

import bee_core.download.DownLoader;
import bee_core.linker.Page;
import bee_core.linker.Request;
import bee_core.processor.Task;

import java.util.logging.Logger;

/**
 * data 2017-03-23   23:43
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class HttpClientDownloader implements DownLoader {
    private Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Override
    public Page download(Request request, Task task) {
        logger.info("this is LOG4J ~!");
        Page page = new Page();
        return page;
    }
}
