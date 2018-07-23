package com.goldskyer.traffic.manager.service;

import com.goldskyer.traffic.manager.common.Assert;
import com.goldskyer.traffic.manager.common.Errors;
import com.goldskyer.traffic.manager.mapper.AccountMapper;
import com.goldskyer.traffic.manager.pojo.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title TDOD
 * @Description: TODO
 * @Author yuren
 * @Date 2018/7/21 上午9:44
 * @Version V2.0.0
 */
@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public void checkAccountValid(String accountId, String password) {
        Assert.isNotBlank(password,Errors.LOGIN_ERROR,"密码不能为空");
        Account account = accountMapper.getAccountById(accountId);
        Assert.notNull(account, Errors.LOGIN_ERROR,"该账号ID不存在");
        Assert.isTrue(StringUtils.equals(password,account.getPassword()),Errors.LOGIN_ERROR,"请输入正确的密码");
    }
}
