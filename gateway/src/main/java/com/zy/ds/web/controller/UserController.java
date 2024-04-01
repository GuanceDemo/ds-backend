package com.zy.ds.web.controller;

import com.zy.ds.api.OrderService;
import com.zy.ds.api.StoreService;
import com.zy.ds.api.UserService;
import com.zy.ds.common.BookInfoDto;
import com.zy.ds.common.OrderInfoDto;
import com.zy.ds.common.Response;
import com.zy.ds.common.*;
import com.zy.ds.web.vo.BookInfoVO;
import com.zy.ds.web.vo.OrderInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.zy.ds.web.monitor.PrometheusCustomMonitor;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

@Api(tags={"用户接口"}, description = "User APIs")
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @DubboReference(version = "1.0.0")
    private UserService userService;
    @DubboReference(version = "1.0.0")
    private StoreService storeService;
    @DubboReference(version = "1.0.0")
    private OrderService orderService;

    @Resource
    private PrometheusCustomMonitor monitor;


    @ApiOperation("图书查询")
    @GetMapping(value = "/book/{isbn}")
    public @ResponseBody
    BookInfoVO getBookInfo(@ApiParam(name="isbn",value="国际图书编号",required=true) @PathVariable(value = "isbn") Long isbn, HttpServletResponse httpResp) {

        logger.info("get book info, isbn = " + isbn);
        try {
            Response<BookInfoDto> resp = storeService.getBookInfoByIsbn(isbn);
            if (resp.isSuccess()) {
                return new BookInfoVO(resp.getData());
            }
            httpResp.setStatus(HttpStatus.NOT_FOUND.value());
        }
        catch (Exception e) {
            logger.error("storeService exception", e);
            httpResp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    @ApiOperation("图书列表")
    @GetMapping(value = "/book/all")
    public @ResponseBody
    List<BookInfoVO> listBook(HttpServletResponse httpResp) {

        logger.info("get all books.");

        try {
            Response<List<BookInfoDto>> resp = storeService.getAllBooks();
            if (resp.isSuccess() && !CollectionUtils.isEmpty(resp.getData())) {

                List<BookInfoVO> voList = new ArrayList<>(resp.getData().size());
                resp.getData().forEach(dto -> {
                    voList.add(new BookInfoVO(dto));
                });
                return voList;
            }
            httpResp.setStatus(HttpStatus.NOT_FOUND.value());
        }
        catch (Exception e) {
            logger.error("storeService exception", e);
            httpResp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    @ApiOperation("购书")
    @GetMapping(value = "/book/purchase")
    public @ResponseBody
    String purchaseBook(@ApiParam(name="userId",value="用户 ID",required=true) @RequestParam Long userId,
                        @ApiParam(name="isbn",value="国际图书编号",required=true) @RequestParam Long isbn,
                        HttpServletResponse httpResp) {

        logger.info("user id = {} purchase book isbn = {}", userId, isbn);

        try {
            Response<OrderInfoDto> resp = orderService.purchaseBook(userId, isbn);
            if (resp.isFail()) {
                httpResp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            monitor.getOrderCount().increment();
            return resp.getMsg();
        }
        catch (Exception e) {
            logger.error("orderService exception", e);
            httpResp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    @ApiOperation("用户订单查询")
    @GetMapping(value = "/order/{userId}")
    public @ResponseBody
    List<OrderInfoVO> getUserOrderInfo(@ApiParam(name="userId",value="用户 ID",required=true) @PathVariable(value = "userId") Long userId, HttpServletResponse httpResp) {

        logger.info("user id = {} query order", userId);

        try {
            Response<List<OrderInfoDto>> resp = orderService.queryOrder(userId);
            if (resp.isSuccess() && !CollectionUtils.isEmpty(resp.getData())) {

                List<OrderInfoVO> voList = new ArrayList<>(resp.getData().size());
                resp.getData().forEach(dto -> {
                    voList.add(new OrderInfoVO(dto));
                });
                return voList;
            }
            httpResp.setStatus(HttpStatus.NOT_FOUND.value());
        }
        catch (Exception e) {
            logger.error("orderService exception", e);
            httpResp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }
}
