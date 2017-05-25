package com.cache.redis.service.impl;

import com.cache.redis.domain.UserInfo;
import com.cache.redis.mapper.UserMapper;
import com.cache.redis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "cacheDao", key = "#root.targetClass + 'dataMap'")
    @Override
    public List<UserInfo> findAll() {
        LOGGER.info("--------------->>first");
        return userMapper.findAll();
    }

    @Cacheable(value = "cacheDao", key = "#id + 'dataMap'")
    @Override
    public UserInfo findOne(int id) {
        return userMapper.findOne(id);
    }

}
