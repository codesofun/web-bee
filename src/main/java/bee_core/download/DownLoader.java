package bee_core.download;

import bee_core.linker.Page;
import bee_core.linker.Request;
import bee_core.processor.Task;

/**
 * @author sis.nonacosa
 * E-mail  sis.nonacosa@gmail.com
 */
public interface DownLoader {

    //TODO should add task interface?

    /**
     * 下载页面
     * @param request
     * @param task
     * @return
     */
    Page download(Request request, Task task);
}
