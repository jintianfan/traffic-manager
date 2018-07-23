package com.goldskyer.traffic.manager.mapper;

import java.util.List;

import com.goldskyer.traffic.manager.pojo.City;
import org.apache.ibatis.annotations.Mapper;

/*
 * Mapper接口，这里需要加上Mapper注解
 * */
@Mapper
public interface CityMapper {
	
	List<City> selectAll();
	
}
