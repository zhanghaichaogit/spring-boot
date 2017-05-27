package com.cache.redis.service;

import com.cache.redis.domain.TUser;
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
