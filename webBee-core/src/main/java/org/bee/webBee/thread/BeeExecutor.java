package org.bee.webBee.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * data 2017-06-21   00:21
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public interface BeeExecutor {

     ExecutorService newCachedThreadPool();

     ExecutorService newFixedThreadPool(int threadNum);
}
