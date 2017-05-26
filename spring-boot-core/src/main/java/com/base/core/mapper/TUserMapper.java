package com.base.core.mapper;

import com.base.core.domain.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhanghaichao on 2017/5/25.
 */
@Mapper
public interface TUserMapper {

    List<TUser> findAll();

}
