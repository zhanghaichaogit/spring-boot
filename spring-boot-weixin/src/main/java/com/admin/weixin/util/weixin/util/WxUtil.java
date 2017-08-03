package com.admin.weixin.util.weixin.util;

import com.admin.weixin.entity.wx.WxJssdkEntity;
import com.admin.weixin.entity.wx.WxParBtnEntity;
import com.admin.weixin.entity.wx.WxResultEntity;
import com.admin.weixin.entity.wx.WxTockenEntity;
import com.admin.weixin.util.code.SHA;
import com.admin.weixin.util.weixin.api.WeixinApi;

import java.util.Date;
import java.util.UUID;

/**
 * 微信工具类
 * @author zhanghaichao on 2017/8/3.
 */
public class WxUtil {

  /**
   * 获取微信tocken
   * @return WxTockenEntity
   */
  public static WxTockenEntity getTocken() {
    return WeixinApi.getTocken();
  }

  /**
   * 创建底部菜单
   * @param wxParBtnEntity 菜单按钮实体
   * @return 返回是否创建成功
   */
  public static WxResultEntity menueCreate(WxParBtnEntity wxParBtnEntity) {
    return WeixinApi.menueCreate(wxParBtnEntity);
  }

  /**
   * 获取菜单接口
   * @return 返回WxParBtnEntity
   */
  public static WxParBtnEntity getMenue() {
    return WeixinApi.getMenue();
  }

  /**
   * 生成JS-SDK权限验证的签名 以及调用微信jssdk的所有参数
   * @param url 当前页面的url
   * @return WxJssdkEntity
   */
  public static WxJssdkEntity getJsapiTicket(String url) {
    WxJssdkEntity wxJssdkEntity = new WxJssdkEntity();
    try {
      String ticket = WeixinApi.getJsapiTicket().getTicket();
      String noncestr = UUID.randomUUID().toString();
      Long time = new Date().getTime();
      String str = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + time + "&url=" + url;
      String signature = SHA.encryptSHA(str);

      wxJssdkEntity.setAppId(WeixinApi.getAppId());
      wxJssdkEntity.setNonceStr(noncestr);
      wxJssdkEntity.setSignature(signature);
      wxJssdkEntity.setTimestamp(time);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return wxJssdkEntity;
  }

}
