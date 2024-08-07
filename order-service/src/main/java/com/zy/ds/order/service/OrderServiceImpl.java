package com.zy.ds.order.service;

import com.zy.ds.common.*;
import com.zy.ds.order.model.OrderInfo;
import com.zy.ds.order.persist.CacheCmpt;
import com.zy.ds.order.persist.OrderPersistCmpt;
import com.zy.ds.order.remote.UserClient;
import com.zy.ds.api.OrderService;
import com.zy.ds.common.*;
import com.zy.ds.order.remote.StoreClient;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@DubboService(version = "${order.service.version}")
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private CacheCmpt cacheCmpt;
    @Resource
    private OrderPersistCmpt orderPersistCmpt;
    @Resource
    private UserClient userClient;
    @Resource
    private StoreClient storeClient;

    public Response<OrderInfoDto> purchaseBook(@NotNull Long userId, @NotNull Long isbn) {

        Response<OrderInfoDto> resp = new Response<OrderInfoDto>(ResponseCode.SUCCESS);
        logger.info("OrderService.buyBook, 用户 userId = {} 购买图书 isbn = {}", userId, isbn);

        Response<UserInfoDto> userResp = userClient.getUserInfoById(userId);
        if (userResp.getCode() != ResponseCode.SUCCESS.getCode() || userResp.getData() == null) {
            logger.warn("用户 id = " + userId + " 不存在！");
            return resp.setMsg("用户 id = " + userId + " 不存在！");
        }

        Response<BookInfoDto> storeResp = storeClient.getBookInfoByIsbn(isbn);
        if (storeResp.getCode() != ResponseCode.SUCCESS.getCode() || storeResp.getData() == null) {
            logger.warn("图书 ISBN = " + isbn + " 不存在！");
            return resp.setMsg("图书 ISBN = " + isbn + " 不存在！");
        }

        try {
            // simple but bugable
            Long orderId = System.currentTimeMillis() + userId + isbn;

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(orderId);
            orderInfo.setUserId(userResp.getData().getId());
            orderInfo.setUserName(userResp.getData().getName());
            orderInfo.setIsbn(storeResp.getData().getIsbn());
            orderInfo.setBookTitle(storeResp.getData().getTitle());
            orderInfo.setAuthor(storeResp.getData().getAuthor());
            orderInfo.setPrice(storeResp.getData().getPrice());
            orderInfo.setPurchaseDate(new Date());

            orderPersistCmpt.saveOrderInfo(orderInfo);

            logger.info("orderId:{},{}",orderId,orderId%3);
            if (orderId%3==0){
                throw new RuntimeException("订购异常");
            }
            resp.setMsg("购买成功");

            OrderInfoDto orderInfoDto = new OrderInfoDto();
            BeanUtils.copyProperties(orderInfo, orderInfoDto);

            cacheCmpt.cashUserOrder(userId, orderInfoDto);
        }
        catch (Exception e) {
            logger.error("exception during checkOutBook", e);
            resp.setFailue("服务器异常，请稍后重试");
        }
        return resp;
    }

    public Response<List<OrderInfoDto>> queryOrder(@NotNull Long userId) {

        Response<List<OrderInfoDto>> resp = new Response<List<OrderInfoDto>>(ResponseCode.SUCCESS);
        logger.info("OrderService.queryOrder(), userId = {}", userId);

        try {
            List<OrderInfoDto> dtoList = cacheCmpt.getUserOrders(userId);
            if (null != dtoList) {
                return resp.setData(dtoList);
            }

            List<OrderInfo> orderList = orderPersistCmpt.getOrderByUserId(userId);
            if (CollectionUtils.isEmpty(orderList)) {
                return resp.setFailue("无借书记录！");
            }

            dtoList = new ArrayList<OrderInfoDto>(orderList.size());
            for (OrderInfo entity : orderList) {
                OrderInfoDto dto = new OrderInfoDto();
                BeanUtils.copyProperties(entity, dto);
                dtoList.add(dto);
            }
            resp.setData(dtoList);

            // set in redis
            cacheCmpt.cashOrders(userId, dtoList);
        }
        catch (Exception e) {
            logger.error("exception during queryOrder", e);
            resp.setFailue("服务器异常，请稍后重试");
        }
        return resp;
    }
}
