package com.goldskyer.traffic.manager.web;

import com.goldskyer.traffic.manager.common.Assert;
import com.goldskyer.traffic.manager.common.Errors;
import com.goldskyer.traffic.manager.common.WebApiResponse;
import com.goldskyer.traffic.manager.pojo.City;
import com.goldskyer.traffic.manager.service.AccountService;
import com.goldskyer.traffic.manager.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * SpringMVC控制器
 * */
@Controller
public class LoginController {

	@Autowired
	private AccountService accountService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpSession httpSession) {
		request.setAttribute("metronicPath","/static");
		httpSession.setAttribute("rightMap",new HashMap());
		return "user_login";
	}


	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpSession httpSession) {
		request.setAttribute("metronicPath","/static");
		httpSession.setAttribute("rightMap",new HashMap());
		//退出登录，仍然回到登录页，但是清除session
		httpSession.invalidate();
		return "user_login";
	}



}
