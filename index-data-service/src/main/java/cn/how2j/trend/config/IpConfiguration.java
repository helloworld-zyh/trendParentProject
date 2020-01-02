package cn.how2j.trend.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    /**
     * @program: trendParentProject
     * @description: 和代码微服务一样
     * @author: Zhang Yu He
     * @create: 2020-01-02 15:55
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
