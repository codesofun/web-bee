package thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * data 2017-06-20   22:45
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Executor {

        public static void main(String[] args){
            //ExecutorService 的生命周期包括三种状态：运行、关闭、终止。创建后便进入运行状态，当调用了 shutdown（）方法时
            // ，便进入关闭状态，此时意味着 ExecutorService 不再接受新的任务，但它还在执行已经提交了的任务
          ExecutorService executorService = Executors.newCachedThreadPool();
//          ExecutorService executorService = Executors.newFixedThreadPool(5);
            //创建一个单线程化的Executor。
//          ExecutorService executorService = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 5; i++){
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + "线程被调用了。");
                    }
                });
                System.out.println("************* a" + i + " *************");
            }
            executorService.shutdown();
        }
    }

    class TestRunnable implements Runnable{
        public void run(){
            System.out.println(Thread.currentThread().getName() + "线程被调用了。");
        }
    }
