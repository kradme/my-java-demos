package cn.lang.test;

import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class Son extends Father {
    private int age;

    public Son(Father father){
        super();
        BeanUtils.copyProperties(father, this);
    }
}
