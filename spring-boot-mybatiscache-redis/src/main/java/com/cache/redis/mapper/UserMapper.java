package com.cache.redis.mapper;

import com.cache.redis.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * findOne
     *
     * @param id
     * @return
     */
    @Select(value = "select *from boot_user where id=#{id}")
    UserInfo findOne(int id);

    /**
     * findAll
     *
     * @return
     */
    List<UserInfo> findAll();
}
