package cn.lang.juc.disruptor.demo;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory {

    public LongEvent newInstance(){
        return new LongEvent();
    }
}
