package com.base.core.service.impl;

import com.base.core.domain.UserInfo;
import com.base.core.mapper.UserMapper;
import com.base.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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
