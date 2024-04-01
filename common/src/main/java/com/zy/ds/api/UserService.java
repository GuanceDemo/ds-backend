package com.zy.ds.api;

import com.zy.ds.common.Response;
import com.zy.ds.common.UserInfoDto;

import java.util.List;

public interface UserService {

    Response<UserInfoDto> getUserInfoById(Long userId);
    Response<List<UserInfoDto>> getUserInfoByName(String userName);
    Response<String> addUser(UserInfoDto userInfoDto);
    Response<String> modifyUserInfo(UserInfoDto UserInfoDto);
    Response<String> deleteUserById(Long userId);

}