package com.zy.ds.order.persist;

import com.zy.ds.order.model.OrderInfo;

import java.util.List;

public interface OrderPersistCmpt {

    int saveOrderInfo(OrderInfo orderInfo);
    List<OrderInfo> getOrderByUserId(Long userId);
}
