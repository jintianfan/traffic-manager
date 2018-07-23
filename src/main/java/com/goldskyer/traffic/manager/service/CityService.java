package com.goldskyer.traffic.manager.service;

import java.util.List;

import com.goldskyer.traffic.manager.pojo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.traffic.manager.mapper.CityMapper;

/*
 * 服务层
 * */
@Service
public class CityService {

	@Autowired
	private CityMapper cityMapper;
	
	
	public List<City> list(){
		return cityMapper.selectAll();
	}
	
	
}
