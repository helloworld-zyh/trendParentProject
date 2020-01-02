package cn.how2j.trend.service;

import cn.how2j.trend.pojo.IndexData;
import cn.hutool.core.collection.CollUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
/**
 * @program: trendParentProject
 *
 * @description: 从redis获取数据
 *
 * @author: Zhang Yu He
 *
 * @create: 2020-01-02 15:58
 **/
@Cacheable(key = "'indexData-code-'+#p0")
    public List<IndexData> get(String code){
    return CollUtil.toList();
}
}
