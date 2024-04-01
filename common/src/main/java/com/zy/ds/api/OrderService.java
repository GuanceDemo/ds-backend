package com.zy.ds.api;

import com.zy.ds.common.OrderInfoDto;
import com.zy.ds.common.Response;

import java.util.List;

public interface OrderService {

    Response<OrderInfoDto> purchaseBook(Long userId, Long isbn);

    Response<List<OrderInfoDto>> queryOrder(Long userId);
}
