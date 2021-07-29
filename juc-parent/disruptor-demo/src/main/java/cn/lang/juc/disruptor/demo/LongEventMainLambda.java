package cn.lang.juc.disruptor.demo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class LongEventMainLambda {
    public static void main(String[] args) {
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, 1024, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith((event, seq, byteBuff)-> System.out.println(event));
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long i = 0; i < 100; i++) {
            byteBuffer.putLong(0,i);
            ringBuffer.publishEvent((event, seq, buff)->event.setValue(buff.getLong(0)), byteBuffer);
        }
    }
}
