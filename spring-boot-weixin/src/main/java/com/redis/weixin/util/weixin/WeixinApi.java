package com.redis.weixin.util.weixin;

import com.alibaba.fastjson.JSONObject;
import com.redis.weixin.entity.wx.WxTockenEntity;
import com.redis.weixin.util.http.HttpUtil;
import com.redis.weixin.util.json.JsonUtil;

import java.io.IOException;

/**
 * Created by zhanghaichao on 2017/7/17.
 */
public class WeixinApi {


    private static final String appid = "wxbdfdac829ca8f1ff";
    private static final String secret = "7d7a2817d7717577a2bf64c6ca943a55";

    public static WxTockenEntity getTocken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String tocken = "";
        WxTockenEntity wxTockenEntity = new WxTockenEntity();
        try {
            tocken = HttpUtil.getHttp(url);
            wxTockenEntity = JsonUtil.toBean(tocken, WxTockenEntity.class);
            JSONObject.parseObject(tocken, WxTockenEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
            wxTockenEntity.setAccess_token(e.getMessage());
            wxTockenEntity.setExpires_in(0);
        }


        return wxTockenEntity;
    }

    public static String menueCreate(String menuejson) {

        String tockenGet = getTocken().getAccess_token();
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + tockenGet;
        System.out.println(url);
        String result = "";
        try {
            result = HttpUtil.postHttp(url, menuejson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
