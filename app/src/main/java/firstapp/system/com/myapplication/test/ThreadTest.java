package firstapp.system.com.myapplication.test;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * private final BlockingQueue<Runnable> workQueue;              //任务缓存队列，用来存放等待执行的任务
 private final ReentrantLock mainLock = new ReentrantLock();   //线程池的主要状态锁，对线程池状态（比如线程池大小
 //、runState等）的改变都要使用这个锁
 private final HashSet<Worker> workers = new HashSet<Worker>();  //用来存放工作集

 private volatile long  keepAliveTime;    //线程存活时间
 private volatile boolean allowCoreThreadTimeOut;   //是否允许为核心线程设置存活时间
 private volatile int   corePoolSize;     //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
 private volatile int   maximumPoolSize;   //线程池最大能容忍的线程数

 private volatile int   poolSize;       //线程池中当前的线程数

 private volatile RejectedExecutionHandler handler; //任务拒绝策略

 private volatile ThreadFactory threadFactory;   //线程工厂，用来创建线程

 private int largestPoolSize;   //用来记录线程池中曾经出现过的最大线程数

 private long completedTaskCount;   //用来记录已经执行完毕的任务个数
 * 线程池使用
 * Created by user on 2017/2/16.
 * author 王涛
 */

public class ThreadTest
{
    private static final String TAG = "ThreadTest";
    @Test
    public void newCachedThreadPool(){
        //当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i< 10; i++){
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            threadPool.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    int i = 0;
                    i++;
                    System.out.println("第"+i+"次"+Thread.currentThread().getId()+"  ");

                }
            });

        }
        threadPool.shutdown();
    }

    @Test
    public void newFixedThreadPool(){
        //创建一个定长线程池，可控制线程最大并发数，线程池一旦达到最大限制，超出的线程会在队列中等待。
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getId()+"  ");
            }
        });
        threadPool.execute(new Runnable()
        {
            @Override
            public void run()
            {

                System.out.println(Thread.currentThread().getId()+"  ");
            }
        });
        threadPool.execute(new Runnable()
        {
            @Override
            public void run()
            {

                System.out.println(Thread.currentThread().getId()+"  ");
            }
        });

        //关闭线程池
        threadPool.shutdown();
    }
    @Test
    public void newScheduledThreadPool (){
        //创建一个定长线程池，它可安排在给定延迟后运行命令或者定期地执行。
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2);
//        TimeUnit.DAYS;               //天
//        TimeUnit.HOURS;             //小时
//        TimeUnit.MINUTES;           //分钟
//        TimeUnit.SECONDS;           //秒
//        TimeUnit.MILLISECONDS;      //毫秒
//        TimeUnit.MICROSECONDS;      //微妙
//        TimeUnit.NANOSECONDS;       //纳秒
        poolExecutor.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("============================");
            }
        },1000,5000, TimeUnit.MILLISECONDS);
        poolExecutor.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(System.nanoTime()+"");
            }
        },1000,5000, TimeUnit.MILLISECONDS);
        System.out.println("最大线程池："+poolExecutor.getLargestPoolSize());
        System.out.println("记录已执行线程数："+poolExecutor.getCompletedTaskCount());
        //关闭线程池
        poolExecutor.shutdown();
    }
    @Test
    public void newSingleThreadExecutor(){
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(System.nanoTime()+"  "+Thread.currentThread().getName()+"   "+Thread.currentThread
                        ().getId()+"  "+Thread.currentThread().getPriority());
            }
        });
        executorService.execute(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(System.nanoTime()+"  "+Thread.currentThread().getName()+"   "+Thread.currentThread
                        ().getId()+"  "+Thread.currentThread().getPriority());
            }
        });
        executorService.execute(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(System.nanoTime()+"  "+Thread.currentThread().getName()+"   "+Thread.currentThread
                        ().getId());
            }
        });
        executorService.execute(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(System.nanoTime()+"  "+Thread.currentThread().getName()+"   "+Thread.currentThread
                        ().getId());
            }
        });
        //关闭线程池
        executorService.shutdown();
    }

}
