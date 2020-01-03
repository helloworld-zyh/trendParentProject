package cn.how2j.trend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
/**
 * @program: trendParentProject
 *
 * @description: 控制类
 *
 * @author: Zhang Yu He
 *
 * @create: 2020-01-03 10:41
 **/
@GetMapping("/")
    public String view()throws Exception{
    return "view";
}
}
