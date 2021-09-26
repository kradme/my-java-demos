package cn.lang.juc.atomic.reference;

import lombok.Data;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Data
public class Demo {

    private Integer value;

    public Demo(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demo demo = (Demo) o;
        return Objects.equals(value, demo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static void main(String[] args) {
        AtomicReference<Demo> atomicReference = new AtomicReference<>();
        Demo demo = new Demo(0);
        atomicReference.set(demo);
        boolean b = atomicReference.compareAndSet(new Demo(0), new Demo(1));
        Demo result = atomicReference.get();
        Assert.isTrue(b, "xxx");
    }
}
