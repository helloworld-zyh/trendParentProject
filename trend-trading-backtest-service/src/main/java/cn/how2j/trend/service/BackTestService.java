package cn.how2j.trend.service;

import cn.how2j.trend.client.IndexDataClient;
import cn.how2j.trend.pojo.IndexData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BackTestService {
    /**
     * @program: trendParentProject
     * @description: 这就是用于提供所有模拟回测数据的微服务了。 当然，现在比较简单，随着业务的增加和功能的迭代，就会越来愈丰富起来了。 站长用这种由浅入深的方式来把这个复杂的服务逐渐发展起来，大家通过这个过程参与进来会更加容易理解和消化。
     * @author: Zhang Yu He
     * @create: 2020-01-03 09:15
     **/
    @Autowired
    IndexDataClient indexDataClient;

    public List<IndexData> listIndexData(String code){
        List<IndexData> result = indexDataClient.getIndexData(code);
        Collections.reverse(result);

        for (IndexData indexData:result){
            System.out.println(indexData.getDate());
        }
        return result;
    }
}
