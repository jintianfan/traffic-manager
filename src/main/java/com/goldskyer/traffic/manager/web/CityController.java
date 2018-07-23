package com.goldskyer.traffic.manager.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldskyer.traffic.manager.pojo.City;
import com.goldskyer.traffic.manager.service.CityService;

import javax.servlet.http.HttpSession;

/*
 * SpringMVC控制器
 * */
@Controller
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping("/")
	public String list(Map<String, Object> model,HttpSession httpSession) {
		httpSession.setAttribute("rightMap",new HashMap());
		return "index";
	}

	@RequestMapping("/city")
	public String city(Map<String, Object> model) {
		List<City> cities = cityService.list();
		model.put("city", cities);
		return "lists";
	}
}
