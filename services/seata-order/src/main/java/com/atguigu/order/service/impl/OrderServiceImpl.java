package com.atguigu.order.service.impl;

import com.atguigu.order.bean.OrderTbl;
import com.atguigu.order.feign.AccountFeignClient;
import com.atguigu.order.mapper.OrderTblMapper;
import com.atguigu.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderTblMapper orderTblMapper;
    @Autowired
    private AccountFeignClient accountFeignClient;

    @Override
    @Transactional // 开启事务
    public OrderTbl create(String userId, String commodityCode, int orderCount) {
        //1、计算订单价格
        int orderMoney = calculate(commodityCode, orderCount);


        log.info("订单金额》》》》》》》》》{}", orderMoney);
        //TODO 2、扣减账户余额
        accountFeignClient.debit(userId, orderMoney);

        //3、保存订单
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setUserId(userId);
        orderTbl.setCommodityCode(commodityCode);
        orderTbl.setCount(orderCount);
        orderTbl.setMoney(orderMoney);

        orderTblMapper.insert(orderTbl);

        int i = 1/0;
        return orderTbl;
    }

    // 计算价格
    private int calculate(String commodityCode, int orderCount) {
        return 9*orderCount;
    }
}
