package com.pro.base.api.impl;

import com.pro.base.api.UserApi;
import com.pro.base.domain.UserInfo;
import com.pro.base.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * * 这个实体以及后面的api都是为了从其他项目中抽离出公共的数据库操作
 */

public class UserApiImpl implements UserApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserApiImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfo> findAll() {
        return userMapper.findAll();
    }

    @Override
    public UserInfo findOne(int id) {
        return userMapper.findOne(id);
    }

}
