package com.base.core.service;

import com.base.core.domain.TUser;
import com.pro.base.domain.UserInfo;

import java.util.List;

public interface UserService {

    /**
     * findAll
     *
     * @return
     */
    List<UserInfo> findAll();

    List<TUser> findAllT();
}
