package com.pro.base.mapper;

import com.pro.base.domain.UserInfo;

import java.util.List;


public interface UserMapper {

    /**
     * findOne
     *
     * @param id
     * @return
     */
    UserInfo findOne(int id);

    /**
     * findAll
     *
     * @return
     */
    List<UserInfo> findAll();
}
