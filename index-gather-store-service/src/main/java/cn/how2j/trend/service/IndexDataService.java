package cn.how2j.trend.service;

import cn.how2j.trend.pojo.IndexData;
import cn.how2j.trend.util.SpringContextUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
    /**
     * @program: trendParentProject
     * @description: 这个和IndexService对应的，只是在这里获取的是指数数据。
     * 需要注意的是：指数数据存放的key是indexData-code-000300这种风格
     * @author: Zhang Yu He
     * @create: 2020-01-02 10:01
     **/
    private Map<String, List<IndexData>> indexDatas = new HashMap<>();

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<IndexData> fresh(String code) {
        //获取数据
        List<IndexData> indexeDatas = fetch_indexes_from_third_part(code);
        indexDatas.put(code,indexeDatas);
        System.out.println("code"+code);
        System.out.println("indexeDatas"+indexeDatas);

        IndexDataService indexDataService = SpringContextUtil.getBean(IndexDataService.class);
        indexDataService.remove(code);
        return indexDataService.store(code);
    }
    //删除数据
    @CacheEvict(key = "'indexData-code-'+#p0")
    public void remove(String code){

    }

    //保存数据
    @CachePut(key = "'indexData-code-'+#p0")
    public List<IndexData> store(String code){
        return indexDatas.get(code);
    }

    //获取数据
    @Cacheable(key = "'indexData-code-'+#p0")
    public List<IndexData> get(String code){
        return CollUtil.toList();
    }

    public List<IndexData> fetch_indexes_from_third_part(String code) {
        List<Map> temp = restTemplate.getForObject("http://127.0.0.1:8090/indexes/" + code + ".json", List.class);
        return map2IndexData(temp);
    }
    private List<IndexData> map2IndexData(List<Map> temp){
        List<IndexData> indexDatas = new ArrayList<>();
        for (Map map:temp){
            String date = map.get("date").toString();
            float closePoint = Convert.toFloat(map.get("closePoint"));
            IndexData indexData = new IndexData();

            indexData.setDate(date);
            indexData.setClosePoint(closePoint);
            indexDatas.add(indexData);
        }
        return indexDatas;
    }

    public List<IndexData> third_part_not_connected(String code){
        System.out.println("third_part_not_connected()");
        IndexData indexData = new IndexData();
        indexData.setDate("n/a");
        indexData.setClosePoint(0);
        return CollectionUtil.toList(indexData);
    }

}
