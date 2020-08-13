package cn.lang.algo;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
public class TestAlgo implements ComplexKeysShardingAlgorithm<Long> {


    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {
        String tableName = complexKeysShardingValue.getLogicTableName();
        Map<String, LinkedList<Object>> columns = complexKeysShardingValue.getColumnNameAndShardingValuesMap();

        Collection<String> table = new LinkedList<>();

        /**
         * schoolId
         * dayTime
         */
        LinkedList<Object> schoolIdColumnValues = columns.get("order_id");
        LinkedList<Object> dayTimeColumnValues = columns.get("month_time");
        for (int i = 0; i < dayTimeColumnValues.size(); i++) {
            StringBuilder realTable = new StringBuilder(tableName);
            String monthYear = (String) dayTimeColumnValues.get(i);
//            DateTimeLiteralExpression.DateTime yyyyMM = new SimpleDateFormat("yyyyMM").format(monthYear);
//            int year = yyyyMM.getYear();
//            int month = yyyyMM.getMonthOfYear();


//            realTable.append("_").append(schoolIdColumnValues.get(0)).append("_").append(year).append("_").append(month);
            table.add(realTable.toString());
        }
        log.info("#TableShardingAlgorithm# realTableName:{}", table);
        return table;
    }
}
