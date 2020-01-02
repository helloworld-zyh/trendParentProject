package cn.how2j.trend.web;

import cn.how2j.trend.config.IpConfiguration;
import cn.how2j.trend.pojo.IndexData;
import cn.how2j.trend.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexDataController {
    /**
     * @program: trendParentProject
     * @description: 提供web接口
     * @author: Zhang Yu He
     * @create: 2020-01-02 16:02
     **/

    //  http://127.0.0.1:8021/data/000300
    @Autowired
    IndexDataService indexDataService;
    @Autowired
    IpConfiguration ipConfiguration;

    @GetMapping("/data/{code}")
    public List<IndexData> get(@PathVariable("code") String code ){
        System.out.println("current instance is:"+ipConfiguration.getPort());
        return indexDataService.get(code);
    }
}
