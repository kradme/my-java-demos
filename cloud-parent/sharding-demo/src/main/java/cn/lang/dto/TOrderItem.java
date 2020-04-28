package cn.lang.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_order_item")
public class TOrderItem {
    @TableId("item_id")
    private long itemId;
    @TableField("item_name")
    private String itemName;
    @TableField("item_pric")
    private BigDecimal itemPric;
}
