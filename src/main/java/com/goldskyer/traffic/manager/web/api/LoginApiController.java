package com.goldskyer.traffic.manager.web.api;

import com.goldskyer.traffic.manager.SessionKey;
import com.goldskyer.traffic.manager.common.Assert;
import com.goldskyer.traffic.manager.common.Errors;
import com.goldskyer.traffic.manager.common.WebApiResponse;
import com.goldskyer.traffic.manager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.nio.channels.AcceptPendingException;

/**
 * @Title TDOD
 * @Description: TODO
 * @Author yuren
 * @Date 2018/7/21 上午10:56
 * @Version V2.0.0
 */
@RestController
public class LoginApiController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/api/doLogin")
    @ResponseBody
    public Object doLogin(@RequestParam(required = false) String username,@RequestParam(required = false) String password,HttpSession httpSession) {
        Assert.isNotBlank(username, Errors.LOGIN_ERROR,"用户名不能为空");
        Assert.isNotBlank(password, Errors.LOGIN_ERROR,"密码不能为空");
        accountService.checkAccountValid(username,password);
        //登录成功设置各种参数
        httpSession.setAttribute(SessionKey.ACCOUNT_ID,username);
        return WebApiResponse.success("登录成功");
    }
}
