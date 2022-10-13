package cn.lang.test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    private static ThreadLocal<Father> threadLocal = new ThreadLocal();
    private static ThreadLocal<String> threadLocal2 = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(new Father("a"));
        threadLocal2.set("xxxxx");
        System.gc();
        System.out.println(threadLocal.get());
    }
}
