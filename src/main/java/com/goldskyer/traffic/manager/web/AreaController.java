package com.goldskyer.traffic.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Title 禁行区设置
 * @Description: TODO
 * @Author yuren
 * @Date 2018/7/21 上午12:22
 * @Version V2.0.0
 */
@Controller
public class AreaController {

    @RequestMapping("/area/view")
    public String view(HttpServletRequest request,HttpSession httpSession) {
        httpSession.setAttribute("rightMap",new HashMap());
        return "area/view";
    }
}
