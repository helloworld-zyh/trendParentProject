package cn.how2j.trend.web;

import cn.how2j.trend.pojo.IndexData;
import cn.how2j.trend.service.BackTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BackTestController {
    /**
     * @program: trendParentProject
     * @description: 控制器，返回的数据是放在一个 Map 里的，而目前的key是 indexDatas。
     * 因为将来会返回各种各样的数据，通过这种方式才好区分不同的数据。
     * @author: Zhang Yu He
     * @create: 2020-01-03 09:18
     **/
    @Autowired
    BackTestService backTestService;

    @GetMapping("/simulate/{code}")
    @CrossOrigin
    public Map<String,Object> backTest(@PathVariable("code") String code)throws Exception{
        List<IndexData> allIndexDatas = backTestService.listIndexData(code);
        Map<String,Object> result = new HashMap<>();
        result.put("indexDatas",allIndexDatas);
        return result;
    }

}
