package com.base.core.service;

import com.base.core.domain.UserInfo;

import java.util.List;

public interface UserService {

    /**
     * findAll
     *
     * @return
     */
    List<UserInfo> findAll();

    UserInfo findOne(int id);
}
