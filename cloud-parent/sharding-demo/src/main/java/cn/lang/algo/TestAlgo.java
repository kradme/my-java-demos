package cn.lang.algo;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

@Slf4j
public class TestAlgo implements ComplexKeysShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        log.debug("收到分片：{}", collection);
        if (CollectionUtils.isEmpty(collection)) return null;
        //从上下文中获取分片ID
        if(){

        }
        return null;
    }
}
