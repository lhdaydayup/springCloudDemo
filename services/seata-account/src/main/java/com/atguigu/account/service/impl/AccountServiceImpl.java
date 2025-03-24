package com.atguigu.account.service.impl;

import com.atguigu.account.mapper.AccountTblMapper;
import com.atguigu.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountTblMapper accountTblMapper;
    @Override
    @Transactional // 开启事务
    public void debit(String userId, int money) {
        log.info("用户id：{}》》》》》》购买金额：{}",userId,money);
        // 扣减账户余额
        accountTblMapper.debit(userId,money);
    }
}
