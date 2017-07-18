package com.redis.weixin.entity.wx;

import com.redis.weixin.entity.BaseEntity;

/**
 * 微信tocken实体
 *
 * @Author zhanghaichao on 2017/7/18.
 */
public class WxTockenEntity extends BaseEntity {
    private static final long serialVersionUID = -5651807479926844562L;
    /**
     * 微信tocken
     */
    private String access_token;

    /**
     * 过期时间
     */
    private Integer expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}
