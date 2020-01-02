package cn.how2j.trend.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    /**
     * @program: trendParentProject
     * @description: 用于获取当前的端口号，因为这个微服务会做出集群，
     * 不同的微服务使用的是不同的端口号，可以通过获取打印出来知道当前是哪个
     * @author: Zhang Yu He
     * @create: 2020-01-02 11:44
     **/
    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.serverPort = webServerInitializedEvent.getWebServer().getPort();
    }

    public int getPort() {
        return this.serverPort;
    }
}
