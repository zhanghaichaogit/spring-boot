package com.redis.weixin.entity.wx;

import com.redis.weixin.entity.BaseEntity;

import java.util.List;

/**
 * 微信组合参数使用
 *
 * @Author zhanghaichao
 */
public class WxParBtnEntity extends BaseEntity {
    private static final long serialVersionUID = -3274802316870793170L;
    /**
     * 一级菜单数组，个数应为1~3个
     */
    private List<WxTopBtnEntity> button;

    public List<WxTopBtnEntity> getButton() {
        return button;
    }

    public void setButton(List<WxTopBtnEntity> button) {
        this.button = button;
    }
}
