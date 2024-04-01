package com.zy.ds.order.remote;

import com.zy.ds.api.UserService;
import com.zy.ds.common.Response;
import com.zy.ds.common.ResponseCode;
import com.zy.ds.common.UserInfoDto;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("userClient")
public class UserClient {

    private static final Logger log = LoggerFactory.getLogger(UserClient.class);

    @DubboReference(version = "1.0.0")
    private UserService userService;


    public Response<UserInfoDto> getUserInfoById(Long userId) {

        try {
            return userService.getUserInfoById(userId);
        }
        catch (Exception e) {
            log.error("user-service exception when userService.getUserInfoById({})", userId, e);
            return new Response<UserInfoDto>(ResponseCode.FAILURE).setMsg("user-service exception");
        }
    }
}
