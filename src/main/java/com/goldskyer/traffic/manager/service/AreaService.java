package com.goldskyer.traffic.manager.service;

import com.goldskyer.traffic.manager.mapper.AreaMapper;
import com.goldskyer.traffic.manager.pojo.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Title TDOD
 * @Description: TODO
 * @Author yuren
 * @Date 2018/7/21 下午4:30
 * @Version V2.0.0
 */
@Service
public class AreaService {
    @Autowired
    private AreaMapper areaMapper;

    public void addArea(Area area){
        area.setCreateDate(new Date());
        area.setUpdateDate(new Date());
        area.setDeleted(false);
        areaMapper.insert(area);
    }

    public Area getAreaById(long id){
        return areaMapper.getAreaById(id);
    }
}
