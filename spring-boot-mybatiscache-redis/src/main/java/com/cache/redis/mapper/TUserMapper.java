package com.cache.redis.mapper;

import com.cache.redis.domain.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhanghaichao on 2017/5/25.
 */
@Mapper
public interface TUserMapper {

    List<TUser> findAll();

}
