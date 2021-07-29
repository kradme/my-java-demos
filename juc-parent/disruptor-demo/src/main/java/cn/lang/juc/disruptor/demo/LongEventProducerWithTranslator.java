package cn.lang.juc.disruptor.demo;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 发布事件的首选是通过api的时间发布者、时间翻译器
 *
 * 这种方法的另一个优点是可以将翻译器代码拉到一个单独的类中，并且可以轻松地独立进行单元测试。
 * Disruptor 提供了许多不同的接口（EventTranslator、EventTranslatorOneArg、EventTranslatorTwoArg 等），
 * 可以实现这些接口以提供转换器。原因是允许将翻译器表示为静态类或非捕获 lambda（当 Java 8 推出时），
 * 因为翻译方法的参数通过环形缓冲区上的调用传递给翻译器。
 */
public class LongEventProducerWithTranslator{

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> eventTranslator = (event, sequence, byteBuffer) -> {
        long value = byteBuffer.getLong(0);
        event.setValue(value);
    };

    public void onData(ByteBuffer byteBuffer){
        ringBuffer.publishEvent(eventTranslator, byteBuffer);
    }

}
