package cn.how2j.trend.service;

import cn.how2j.trend.pojo.Index;
import cn.hutool.core.collection.CollUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {
    /**
     * @program: trendParentProject
     * @description: 服务类，直接从redis获取数据。如果没有数据，则会返回“无效指数代码”
     * @author: Zhang Yu He
     * @create: 2020-01-02 14:45
     **/
    private List<Index> indexes;

    @Cacheable(key = "'all_codes'")
    public List<Index> get(){
        Index index = new Index();
        index.setName("无效指数代码");
        index.setCode("000000");
        return CollUtil.toList(index);
    }
}
