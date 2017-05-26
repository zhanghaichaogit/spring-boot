package com.base.core.service.impl;

import com.base.core.domain.TUser;
import com.base.core.mapper.TUserMapper;
import com.base.core.service.UserService;
import com.pro.base.api.impl.UserApiImpl;
import com.pro.base.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public List<UserInfo> findAll() {
        return userApi.findAll();
    }

    @Override
    public List<TUser> findAllT() {
        return tUserMapper.findAll();
    }
}
