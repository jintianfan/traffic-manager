package com.goldskyer.traffic.manager.mapper;

import com.goldskyer.traffic.manager.pojo.Area;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @Title TDOD
 * @Description: TODO
 * @Author yuren
 * @Date 2018/7/21 上午9:42
 * @Version V2.0.0
 */
@Mapper
public interface AreaMapper {

    @Insert("insert into tb_area(id,city,areaName,locations,startTime,endTime,carType,deleted,createDate,updateDate) values "
            + "(#{id},#{city},#{areaName},#{locations},#{startTime},#{endTime},#{carType},#{deleted},#{createDate},#{updateDate})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public void insert(Area area);

    @Select("select * from tb_area where id=#{areaId}")
    public Area getAreaById(long areaId);
}
