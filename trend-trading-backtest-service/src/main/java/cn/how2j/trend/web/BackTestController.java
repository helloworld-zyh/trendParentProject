package cn.how2j.trend.web;

import cn.how2j.trend.pojo.IndexData;
import cn.how2j.trend.service.BackTestService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @GetMapping("/simulate/{code}/{startDate}/{endDate}")
    @CrossOrigin
    public Map<String, Object> backTest(@PathVariable("code") String code,
                                        @PathVariable("startDate") String startDate,
                                        @PathVariable("endDate") String endDate) throws Exception {
        List<IndexData> allIndexDatas = backTestService.listIndexData(code);
        //计算出开始日期和结束日期并返回
        String indexStartDate = allIndexDatas.get(0).getDate();
        String indexEndDate = allIndexDatas.get(allIndexDatas.size() - 1).getDate();
        //根据开始日期和结束日期获取对应日期范围的数据
        allIndexDatas = filterByDateRange(allIndexDatas, indexStartDate, indexEndDate);
        Map<String, Object> result = new HashMap<>();
        result.put("indexDatas", allIndexDatas);
        result.put("indexStartDate", indexStartDate);
        result.put("indexEndDate", indexEndDate);
        return result;
    }

    //根据开始日期和结束日期获取对应日期范围的数据
    private List<IndexData> filterByDateRange(List<IndexData> allIndexDatas, String strStartDate, String strEndDate) {
        if (StrUtil.isBlankOrUndefined(strStartDate) || StrUtil.isBlankOrUndefined(strEndDate))
            return allIndexDatas;

        List<IndexData> result = new ArrayList<>();
        Date startDate = DateUtil.parse(strStartDate);
        Date endDate = DateUtil.parseDate(strEndDate);
        for (IndexData indexData : allIndexDatas) {
            Date date = DateUtil.parse(indexData.getDate());
            if (date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
                result.add(indexData);
            }
        }
        return result;
    }
}
