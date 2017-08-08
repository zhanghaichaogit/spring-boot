package com.admin.weixin.util.weixin.util;

import com.admin.weixin.entity.wx.WxJssdkEntity;
import com.admin.weixin.entity.wx.WxParBtnEntity;
import com.admin.weixin.entity.wx.WxResultEntity;
import com.admin.weixin.entity.wx.WxTmplBaseEntity;
import com.admin.weixin.entity.wx.WxTockenEntity;
import com.admin.weixin.entity.wx.WxUserAccessTokenEntity;
import com.admin.weixin.entity.wx.WxUserInfoEntity;
import com.admin.weixin.util.code.SHA;
import com.admin.weixin.util.weixin.api.WeixinApi;
import com.alibaba.druid.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
  public static WxJssdkEntity WxJssdkSign(String url) {
    WxJssdkEntity wxJssdkEntity = new WxJssdkEntity();
    String nonce_str = UUID.randomUUID().toString();
    String timestamp = Long.toString(System.currentTimeMillis() / 1000);
    String signature = "";
    String jsapi_ticket = WeixinApi.getJsapiTicket().getTicket();
    //注意这里参数名必须全部小写，且必须有序
    String string1 = "jsapi_ticket=" + jsapi_ticket +
        "&noncestr=" + nonce_str +
        "&timestamp=" + timestamp +
        "&url=" + url;
    try {
      MessageDigest crypt = MessageDigest.getInstance("SHA-1");
      crypt.reset();
      crypt.update(string1.getBytes("UTF-8"));
      signature = SHA.byteToHex(crypt.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    wxJssdkEntity.setAppId(WeixinApi.getAppId());
    wxJssdkEntity.setNonceStr(nonce_str);
    wxJssdkEntity.setSignature(signature);
    wxJssdkEntity.setTimestamp(timestamp);

    return wxJssdkEntity;
  }

  /**
   * 返回oauth 链接
   * @param redirectUri 链接地址
   * @return 返回oauth 链接
   */
  public static String getOauthUrl(String redirectUri) {
    String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
        "appid=" + WeixinApi.getAppId() +
        "&redirect_uri=" + redirectUri +
        "&response_type=code" +
        "&scope=snsapi_userinfo" +
        "&state=STATE" +
        "#wechat_redirect";
    return url;
  }

  /**
   * 根据微信code获取用户微信信息
   * @param code 用户微信code
   * @return 返回用户微信信息 WxUserInfoEntity
   */
  public static WxUserInfoEntity getUserInfo(String code) {
    WxUserInfoEntity wxUserInfoEntity = new WxUserInfoEntity();
    WxUserAccessTokenEntity wxUserAccessTokenEntity = WeixinApi.getUserAccessTokenOpenId(code);
    if (!StringUtils.isEmpty(wxUserAccessTokenEntity.getMessage())) {
      wxUserInfoEntity.setMessage(wxUserAccessTokenEntity.getMessage());
      wxUserInfoEntity.setErrcode(wxUserAccessTokenEntity.getErrcode());
      wxUserInfoEntity.setErrmsg(wxUserAccessTokenEntity.getErrmsg());
    } else {
      wxUserInfoEntity = WeixinApi.getWxUserInfo(wxUserAccessTokenEntity.getAccess_token(), wxUserAccessTokenEntity.getOpenid());
    }
    return wxUserInfoEntity;
  }

  /**
   * 推送模板消息
   * @param wxTmplBaseEntity 消息实体
   */
  public static void sendTemplate(WxTmplBaseEntity wxTmplBaseEntity) {
    WeixinApi.sendTemplate(wxTmplBaseEntity);
  }

}
