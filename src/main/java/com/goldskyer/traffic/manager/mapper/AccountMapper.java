package com.goldskyer.traffic.manager.mapper;

import com.goldskyer.traffic.manager.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Title TDOD
 * @Description: TODO
 * @Author yuren
 * @Date 2018/7/21 上午9:37
 * @Version V2.0.0
 */
@Mapper
public interface AccountMapper {

    @Select("select * from tb_account where accountId=#{arg0}")
    public Account getAccountById(String accountId);

}
