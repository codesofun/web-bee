package org.bee.webBee.handler;

/**
 * @author wangtonghe
 * @date 2017/5/3 21:47
 */
public class ConsoleHandler implements Handler {
    @Override
    public void handle(BeeResults beeResult) {

        System.out.println(beeResult);

    }
}
