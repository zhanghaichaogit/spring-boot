package com.redis.weixin.util.weixin;

import com.redis.weixin.util.http.HttpUtil;

import java.io.IOException;

/**
 * Created by zhanghaichao on 2017/7/17.
 */
public class WeixinApi {


    private static final String appid = "wxbdfdac829ca8f1ff";
    private static final String secret = "7d7a2817d7717577a2bf64c6ca943a55";
    private static final String tocken = "hkfPl-OkZGJxfgNpvkl-FL1WcLU-eChRk5kY661NSLugOrvk0cYNPoojW5dX5MAH3PQcswH2fJztXOmedXo9EI4x6q43NvzmX5UQvWsf7u9YrLfvE3FIPA8T_YTBkPrIUPHcAGAHCU";

    public static String getTocken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String tocken = "";
        try {
            tocken = HttpUtil.getHttp(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tocken;
    }

    public static String menueCreate(String menuejson) {

        String tockenGet = getTocken();
        System.out.println(getTocken());
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
