package bee.download;

import bee.Bee;
import bee.linker.Page;
import bee.linker.Request;

/**
 * @author sis.nonacosa
 * E-mail  sis.nonacosa@gmail.com
 */
public interface DownLoader {

    //TODO should add task interface?

    /**
     * 下载页面
     * @param request
     * @param bee
     * @return
     */
    Page download(Request request, Bee bee);
}
