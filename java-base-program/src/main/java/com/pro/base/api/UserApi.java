package com.pro.base.api;

import com.pro.base.domain.UserInfo;

import java.util.List;

public interface UserApi {

    /**
     * findAll
     *
     * @return
     */
    List<UserInfo> findAll();

    UserInfo findOne(int id);
}
