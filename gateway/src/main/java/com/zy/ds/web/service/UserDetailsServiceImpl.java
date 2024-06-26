package com.zy.ds.web.service;

import com.zy.ds.api.UserService;
import com.zy.ds.common.Response;
import com.zy.ds.common.UserInfoDto;
import com.zy.ds.web.entity.JwtUser;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @DubboReference(version = "1.0.0")
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            Response<List<UserInfoDto>> resp = userService.getUserInfoByName(s);
            return new JwtUser(resp.getData().get(0));
        } catch (Exception e) {
            logger.error("userService.getUserInfoByName failed!", e);
            return new JwtUser(0L, "", "", null);
        }

        // curl -X POST -H "Content-Type: application/json" -d '{"name":"guest","password":"guest"}' http://localhost:8090/auth/login
        // curl -X POST -H "Content-Type: application/json" -d '{"name":"guest","password":"guest"}' http://localhost:8090/auth/login
//         return new JwtUser(1L, "guest", "guest", Collections.singleton(new SimpleGrantedAuthority("USER")));
    }
}
