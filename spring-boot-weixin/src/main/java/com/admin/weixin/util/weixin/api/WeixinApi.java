package com.admin.weixin.util.weixin.api;

import com.admin.weixin.constant.WxErrorCodeText;
import com.admin.weixin.entity.wx.WxJssdkTicketEntity;
import com.admin.weixin.entity.wx.WxParBtnEntity;
import com.admin.weixin.entity.wx.WxResultEntity;
import com.admin.weixin.entity.wx.WxTmplBaseEntity;
import com.admin.weixin.entity.wx.WxTockenEntity;
import com.admin.weixin.entity.wx.WxUserAccessTokenEntity;
import com.admin.weixin.entity.wx.WxUserInfoEntity;
import com.admin.weixin.util.http.HttpUtil;
import com.admin.weixin.util.json.JsonUtil;
import com.admin.weixin.util.redis.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * 微信接口
 * Created by zhanghaichao on 2017/7/17.
 */
public class WeixinApi {

  /**
   * 获取微信tocken
   * @param appid 服务号appid
   * @param secret 服务号appid
   * @return WxTockenEntity
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183">获取微信access_token</a>
   */
  public static WxTockenEntity getTocken(String appid, String secret) {
    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
    WxTockenEntity wxTockenEntity = new WxTockenEntity();
    String code = appid + secret;
    String redistocken = RedisUtil.get(code);
    if (StringUtils.isNotBlank(redistocken)) {
      wxTockenEntity.setAccess_token(redistocken);
      return wxTockenEntity;
    }
    try {
      String tocken = HttpUtil.getHttp(url);
      wxTockenEntity = JsonUtil.toBean(tocken, WxTockenEntity.class);
      RedisUtil.set(code, wxTockenEntity.getAccess_token(), 7200l);
    } catch (IOException e) {
      e.printStackTrace();
      wxTockenEntity.setAccess_token(e.getMessage());
      wxTockenEntity.setExpires_in(0);
    }
    return wxTockenEntity;
  }

  /**
   * 创建底部菜单
   * @param wxParBtnEntity 菜单按钮实体
   * @param appid 服务号appid
   * @param secret 服务号appid
   * @return 返回是否创建成功
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013">创建微信菜单</a>
   */
  public static WxResultEntity menueCreate(WxParBtnEntity wxParBtnEntity, String appid, String secret) {
    String menuejson = JsonUtil.toJSONString(wxParBtnEntity);
    String tockenGet = getTocken(appid, secret).getAccess_token();
    String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + tockenGet;
    WxResultEntity wxResultEntity = new WxResultEntity();
    try {
      String result = HttpUtil.postHttp(url, menuejson);
      wxResultEntity = JsonUtil.toBean(result, WxResultEntity.class);
      wxResultEntity.setMessage(WxErrorCodeText.errorMsg(wxResultEntity.getErrcode()));
    } catch (IOException e) {
      wxResultEntity.setMessage(e.getMessage());
    }
    return wxResultEntity;
  }

  /**
   * 获取菜单接口
   * @param appid 服务号appid
   * @param secret 服务号appid
   * @return 返回WxParBtnEntity
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014">获取菜单</a>
   */
  public static WxParBtnEntity getMenue(String appid, String secret) {
    WxParBtnEntity wxParBtnEntity = new WxParBtnEntity();
    try {
      String tockenGet = getTocken(appid, secret).getAccess_token();
      String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + tockenGet;
      String result = HttpUtil.getHttp(url);
      //获取菜单比创建菜单的时候多了一层meun
      JSONObject json = JsonUtil.toJsonObj(result);
      wxParBtnEntity = JsonUtil.toBean(json.getString("menu"), WxParBtnEntity.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return wxParBtnEntity;
  }

  /**
   * 生成JS-SDK权限验证的签名
   * @param appid 服务号appid
   * @param secret 服务号appid
   * @return WxJssdkTicketEntity
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115">生成JS-SDK权限验证的签名</a>
   */
  public static WxJssdkTicketEntity getJsapiTicket(String appid, String secret) {//TODO 后面需要判断WxJssdkTicket是否从缓存中取
    WxJssdkTicketEntity wxJssdkTicketEntity = new WxJssdkTicketEntity();
    try {
      String tockenGet = getTocken(appid, secret).getAccess_token();
      String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + tockenGet + "&type=jsapi";
      String result = HttpUtil.getHttp(url);
      wxJssdkTicketEntity = JsonUtil.toBean(result, WxJssdkTicketEntity.class);
    } catch (IOException e) {
      wxJssdkTicketEntity.setErrmsg(e.getMessage());
    }
    return wxJssdkTicketEntity;
  }

  /**
   * 获取用户的 openid 和 access_token
   * @param code 从微信获取的用户code
   * @param appid 服务号appid
   * @param secret 服务号appid
   * @return WxUserAccessTokenEntity
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842">获取用户的 openid 和 access_token</a>
   */
  public static WxUserAccessTokenEntity getUserAccessTokenOpenId(String code, String appid, String secret) {
    WxUserAccessTokenEntity wxUserAccessTokenEntity = new WxUserAccessTokenEntity();

    try {
      String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
          "appid=" + appid +
          "&secret=" + secret +
          "&code=" + code +
          "&grant_type=authorization_code";
      String result = HttpUtil.getHttp(url);
      wxUserAccessTokenEntity = JsonUtil.toBean(result, WxUserAccessTokenEntity.class);
      if (wxUserAccessTokenEntity.getErrcode() != null) {
        wxUserAccessTokenEntity.setMessage(WxErrorCodeText.errorMsg(wxUserAccessTokenEntity.getErrcode()));
      }
    } catch (IOException e) {
      wxUserAccessTokenEntity.setErrmsg(e.getMessage());
    }
    return wxUserAccessTokenEntity;
  }

  /**
   * 获取用户微信信息
   * @param accessToken accessToken
   * @param openId openId用户唯一标识
   * @return WxUserInfoEntity 用户信息实体
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842">获取用户微信信息</a>
   */
  public static WxUserInfoEntity getWxUserInfo(String accessToken, String openId) {
    WxUserInfoEntity wxUserInfoEntity = new WxUserInfoEntity();
    try {
      String url = "https://api.weixin.qq.com/sns/userinfo?" +
          "access_token=" + accessToken +
          "&openid=" + openId +
          "&lang=zh_CN";
      String result = HttpUtil.getHttp(url);
      wxUserInfoEntity = JsonUtil.toBean(result, WxUserInfoEntity.class);
      if (wxUserInfoEntity.getErrcode() != null) {
        wxUserInfoEntity.setMessage(WxErrorCodeText.errorMsg(wxUserInfoEntity.getErrcode()));
      }
    } catch (IOException e) {
      wxUserInfoEntity.setErrmsg(e.getMessage());
    }
    return wxUserInfoEntity;
  }

  /**
   * 推送模板消息
   * @param wxTmplBaseEntity 消息实体
   * @param appid 服务号appid
   * @param secret 服务号appid
   * @see <a href="https://mp.weixin.qq.com/debug/cgi-bin/readtmpl?t=tmplmsg/faq_tmpl">推送模板消息</a>
   */
  public static void sendTemplate(WxTmplBaseEntity wxTmplBaseEntity, String appid, String secret) {
    try {
      String token = getTocken(appid, secret).getAccess_token();
      String data = wxTmplBaseEntity.toJSON();
      String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
      String result = HttpUtil.postHttp(url, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
