package cn.lang.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_order")
public class TOrder {
    @TableId("order_id")
    private Long orderId;
    private String name;
    private Date date;
    private String orderType;
}
