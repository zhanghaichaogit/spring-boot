package com.admin.weixin.util.weixin;

import com.admin.weixin.constant.WxErrorCodeText;
import com.admin.weixin.entity.wx.WxParBtnEntity;
import com.admin.weixin.entity.wx.WxResultEntity;
import com.admin.weixin.entity.wx.WxTockenEntity;
import com.admin.weixin.util.http.HttpUtil;
import com.admin.weixin.util.json.JsonUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * Created by zhanghaichao on 2017/7/17.
 */
public class WeixinApi {

  //TODO 修改appid和secret的方法
  private static final String appid = "wxbdfdac829ca8f1ff";
  private static final String secret = "7d7a2817d7717577a2bf64c6ca943a55";

  /**
   * 获取微信tocken
   * @return WxTockenEntity
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183">获取微信access_token</a>
   */
  public static WxTockenEntity getTocken() {
    //TODO 这里要加如判断：如过在规定时间内从缓存中获取到tocken则从缓存中取出
    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
    WxTockenEntity wxTockenEntity = new WxTockenEntity();
    try {
      String tocken = HttpUtil.getHttp(url);
      wxTockenEntity = JsonUtil.toBean(tocken, WxTockenEntity.class);
      JSONObject.parseObject(tocken, WxTockenEntity.class);
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
   * @return 返回是否创建成功
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013">创建微信菜单</a>
   */
  public static WxResultEntity menueCreate(WxParBtnEntity wxParBtnEntity) {
    String menuejson = JsonUtil.toJSONString(wxParBtnEntity);
    String tockenGet = getTocken().getAccess_token();
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
   * @return 返回WxParBtnEntity
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014">获取菜单</a>
   */
  public static WxParBtnEntity getMenue() {
    WxParBtnEntity wxParBtnEntity = new WxParBtnEntity();
    try {
      String tockenGet = getTocken().getAccess_token();
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



}
