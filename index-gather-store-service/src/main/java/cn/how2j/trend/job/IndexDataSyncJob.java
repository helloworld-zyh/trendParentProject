package cn.how2j.trend.job;

import cn.how2j.trend.pojo.Index;
import cn.how2j.trend.service.IndexDataService;
import cn.how2j.trend.service.IndexService;
import cn.hutool.core.date.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class IndexDataSyncJob extends QuartzJobBean {
    /**
     * @program: trendParentProject
     * @description: 任务类，定时同时刷新指数数据和指数代码
     * @author: Zhang Yu He
     * @create: 2020-01-02 10:49
     **/
    @Autowired
    IndexService indexService;
    @Autowired
    IndexDataService indexDataService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时启动："+ DateUtil.now());
        List<Index> indexes = indexService.fresh();
        for (Index index :indexes){
            indexDataService.fresh(index.getCode());
        }
        System.out.println("定时结束："+DateUtil.now());
    }
}
