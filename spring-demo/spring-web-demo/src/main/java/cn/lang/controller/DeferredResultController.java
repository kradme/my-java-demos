package cn.lang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

@RestController("/")
public class DeferredResultController {

    @GetMapping("/deferredResult")
    public DeferredResult<String> test() throws InterruptedException {
        final long start = System.currentTimeMillis();
        DeferredResult result = getDeferedResult(start);
        System.out.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+"==================");
        System.out.println(Thread.currentThread()+":"+(System.currentTimeMillis()-start)+":"+result);
        TimeUnit.SECONDS.sleep(15);
        return result;
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
