package com.zy.ds.user.persist;

import com.zy.ds.user.entity.UserInfo;

import java.util.List;

public interface UserPersistCmpt {

    UserInfo getUserById(Long userId);

    List<UserInfo> getUserByName(String userName);

    boolean checkUserByName(String userName);

    int saveUser(UserInfo userInfo);

    int updateUser(UserInfo userInfo);

    int deleteUser(Long id);
}
