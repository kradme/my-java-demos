package cn.lang.juc.disruptor.demo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(new LongEventFactory(), 1024, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducerWithTranslator translator = new LongEventProducerWithTranslator(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long i = 0; i < 100; i++) {
            byteBuffer.putLong(0, i);
            translator.onData(byteBuffer);
            Thread.sleep(100);
        }
    }
}
