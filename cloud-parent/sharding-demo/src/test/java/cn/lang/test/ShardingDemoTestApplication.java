package cn.lang.test;

import cn.lang.dao.OrderDAO;
import cn.lang.dao.OrderItemDAO;
import cn.lang.dto.TOrder;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication
@ComponentScan("cn.lang")
@MapperScan("cn.lang")
public class ShardingDemoTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingDemoTestApplication.class);
    }

    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping("/query")
    @ResponseBody
    public Object query(@RequestParam Long id){
        Map<String, Object> map = new HashMap<>();
        map.put("order_id", id);

        return orderDAO.selectByMap(map);
//        return orderDAO.selectById(id);
    }

    @RequestMapping("/insert")
    @ResponseBody
    @Transactional
    public int insert(@RequestParam String name){
        TOrder tOrder = new TOrder();
        tOrder.setName(name);
        tOrder.setDate(new Date());
        return orderDAO.insert(tOrder);
    }
    @Autowired
    private OrderItemDAO itemDAO;
    @RequestMapping("/queryItem")
    @ResponseBody
    public Object queryItem(@RequestParam long itemId){
        return itemDAO.selectById(itemId);
    }
}
