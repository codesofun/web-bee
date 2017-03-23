package bee.download;

import bee.Bee;
import bee.linker.Page;
import bee.linker.Request;
import bee.processor.Task;

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
