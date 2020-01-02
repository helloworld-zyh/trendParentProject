package cn.how2j.trend.pojo;

import java.io.Serializable;

public class Index implements Serializable {
/**
 * @program: trendParentProject
 *
 * @description: 指数类，用于指数里面的名称和代码
 *
 * @author: Zhang Yu He
 *
 * @create: 2019-12-26 15:44
 **/
    String code ;
    String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
