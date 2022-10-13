package cn.lang.concurrent;

import lombok.SneakyThrows;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

/**
 * springmvc提供的一个异步工具，长轮询使用
 */
public class DeferredResultDemo {

    @SneakyThrows
    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        Object result = getDeferedResult(start).getResult();
        System.out.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+"==================");
        System.out.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+":"+result);
        TimeUnit.SECONDS.sleep(15);
    }

    public static DeferredResult getDeferedResult(long starts){
        DeferredResult<Object> deferredResult = new DeferredResult<Object>(1000L);
        final long start = starts;
        deferredResult.onTimeout(()->{
            System.out.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+":"+"超时");
        });
        deferredResult.onCompletion(()->{
            System.out.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+":"+"处理完成");
        });
        deferredResult.onError(throwable->{
            System.err.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+":"+"处理错误："+throwable.getMessage());
        });
        new Thread(()->{
            System.out.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+":"+"开始");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deferredResult.setResult("业务处理结束");
            System.out.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+":"+"结束");
        }).start();
        return deferredResult;
    }
}
