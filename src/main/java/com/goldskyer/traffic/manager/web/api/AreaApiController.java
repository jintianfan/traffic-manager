package com.goldskyer.traffic.manager.web.api;

import com.goldskyer.traffic.manager.common.Assert;
import com.goldskyer.traffic.manager.common.Errors;
import com.goldskyer.traffic.manager.common.WebApiResponse;
import com.goldskyer.traffic.manager.pojo.Area;
import com.goldskyer.traffic.manager.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title TDOD
 * @Description: TODO
 * @Author yuren
 * @Date 2018/7/21 下午4:31
 * @Version V2.0.0
 */
@RestController
public class AreaApiController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/api/area/addArea")
    @ResponseBody
    public Object addArea(Area area) {
        Assert.notNull(area, Errors.ILLEGAL_PARAMETER);
        Assert.isNotBlank(area.getLocations(), Errors.ILLEGAL_PARAMETER, "添加禁行区域是，区域位置locations不能为空");
        areaService.addArea(area);
        Area exist = areaService.getAreaById(area.getId());
        return WebApiResponse.success(exist);
    }

}
