package cn.how2j.trend.client;

import cn.how2j.trend.pojo.IndexData;
import cn.hutool.core.collection.CollectionUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexDataClientFeignHystrix implements IndexDataClient{
/**
 * @program: trendParentProject
 *
 * @description: IndexDataClientFeignHystrix 实现了 IndexDataClient，
 * 所以就提供了对应的方法，当熔断发生的时候，对应的方法就会被调用了。
 * 这里的方法就是指如果 INDEX-DATA-SERVICE 不可用或者不可访问，
 * 就会返回个 0000-00-00 出去啦。
 *
 * @author: Zhang Yu He
 *
 * @create: 2020-01-03 09:11
 **/
    @Override
    public List<IndexData> getIndexData(String code) {
        IndexData indexData = new IndexData();
        indexData.setClosePoint(0);
        indexData.setDate("0000-00-00");
        return CollectionUtil.toList(indexData);
    }
}
