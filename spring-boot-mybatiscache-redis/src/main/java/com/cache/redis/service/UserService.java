package com.cache.redis.service;

import com.cache.redis.domain.UserInfo;

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
