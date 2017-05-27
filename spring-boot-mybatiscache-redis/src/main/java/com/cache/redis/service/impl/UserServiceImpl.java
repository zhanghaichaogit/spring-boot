package com.cache.redis.service.impl;

import com.cache.redis.domain.TUser;
import com.cache.redis.mapper.TUserMapper;
import com.cache.redis.service.UserService;
import com.pro.base.api.impl.UserApiImpl;
import com.pro.base.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserApiImpl userApi;

    @Resource
    private TUserMapper tUserMapper;

    @Cacheable(value = "cacheDao", key = "#root.targetClass + 'findAll'")
    @Override
    public List<UserInfo> findAll() {
        LOGGER.info("数据库数据库数据库");
        return userApi.findAll();
    }

    @Cacheable(value = "cacheDao", key = "#root.targetClass + 'findAllT'")
    @Override
    public List<TUser> findAllT() {
        LOGGER.info("数据库数据库数据库");
        return tUserMapper.findAll();
    }


}
