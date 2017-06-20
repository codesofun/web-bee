package thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * data 2017-06-20   23:03
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class Collable {

        public static void main(String[] args){
            ExecutorService executorService = Executors.newCachedThreadPool();
            List<Future<String>> resultList = new ArrayList<Future<String>>();

            //创建10个任务并执行
            for (int i = 0; i < 10; i++){
                //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
                Future<String> future = executorService.submit(new TaskWithResult(i));
                //将任务执行结果存储到List中
                resultList.add(future);
            }

            //遍历任务的结果
            for (Future<String> fs : resultList){
                try{
                    while(!fs.isDone());//Future返回如果没有完成，则一直循环等待，直到Future返回完成
                    System.out.println(fs.get());     //打印各个线程（任务）执行的结果
                }catch(InterruptedException e){
                    e.printStackTrace();
                }catch(ExecutionException e){
                    e.printStackTrace();
                }finally{
                    //启动一次顺序关闭，执行以前提交的任务，但不接受新任务
                    executorService.shutdown();
                }
            }
        }
    }

    class TaskWithResult implements Callable<String>{
        private int id;

        public TaskWithResult(int id){
            this.id = id;
        }

        /**
         * 任务的具体过程，一旦任务传给ExecutorService的submit方法，
         * 则该方法自动在一个线程上执行
         */
        public String call() throws Exception {
            System.out.println("call()方法被自动调用！！！    " + Thread.currentThread().getName());
            //该返回结果将被Future的get方法得到
            return "call()方法被自动调用，任务返回的结果是：" + id + "    " + Thread.currentThread().getName();
        }
    }
