package org.bee.webBee.download;


import org.bee.webBee.linker.Page;
import org.bee.webBee.linker.Request;
import org.bee.webBee.processor.Task;

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
