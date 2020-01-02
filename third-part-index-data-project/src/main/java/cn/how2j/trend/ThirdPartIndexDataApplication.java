package cn.how2j.trend;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ThirdPartIndexDataApplication
{
    public static void main( String[] args )
    {
        int port = 8090;
        int eurekaServerPort  = 8761;
        if (NetUtil.isUsableLocalPort(eurekaServerPort)){
            System.out.printf("检查端口%d未启用，判断eureka服务器没有启动，本服务无法使用，故退出%n",eurekaServerPort);
            System.exit(1);
        }
        //这里表示启动的时候如果带了参数，那么程序就会使用8090作为端口号，这样做的好处是，什么代码都不用改，只需要
        // 在启动的时候带不同的参数，就可以启动不同的端口了
        if(null!=args && 0!=args.length) {
            for (String arg : args) {
                if(arg.startsWith("port=")) {
                    String strPort= StrUtil.subAfter(arg, "port=", true);
                    if(NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        }
        if (!NetUtil.isUsableLocalPort(port)){
            System.out.printf("端口%d被占用了，无法启动%n",port);
            System.exit(1);
        }
        new SpringApplicationBuilder(ThirdPartIndexDataApplication.class).properties("server.port="+port).run(args);
        /*System.out.println( "Hello World!" );*/
    }
}
