package cn.how2j.trend.pojo;

public class IndexData {
    /**
     * @program: trendParentProject
     * @description: 指数实体类
     * @author: Zhang Yu He
     * @create: 2020-01-02 09:58
     **/
    String date;
    float closePoint;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getClosePoint() {
        return closePoint;
    }

    public void setClosePoint(float closePoint) {
        this.closePoint = closePoint;
    }
}
