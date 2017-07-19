package com.redis.weixin.web;

import com.redis.weixin.util.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(value = "weixin/")
public class IndexController {
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private String appid = "wxbdfdac829ca8f1ff";
    private String secret = "7d7a2817d7717577a2bf64c6ca943a55";

    /**
     * 获取tocken
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTocken.json", method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public String getTocken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String tocken = "";
        try {
            tocken = HttpUtil.getHttp(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tocken;
    }

}
