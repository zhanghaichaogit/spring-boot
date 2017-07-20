package com.admin.weixin.entity.wx;

import java.util.List;

/**
 * 一级菜单数组，个数应为1~3个`
 *
 * @Author zhanghaichao on 2017/7/18.
 */
public class WxTopBtnEntity extends WxMenueBtnEntity {

    private static final long serialVersionUID = 7873318081520932341L;
    /**
     * 二级菜单数组，个数应为1~5个
     */
    private List<WxSecondaryBtnEntity> sub_button;

    public List<WxSecondaryBtnEntity> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WxSecondaryBtnEntity> sub_button) {
        this.sub_button = sub_button;
    }
}
