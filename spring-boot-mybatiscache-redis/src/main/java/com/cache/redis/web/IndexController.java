package com.cache.redis.web;

import com.alibaba.fastjson.JSON;
import com.cache.redis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IndexController {
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private UserService userService;


    /**
     * 查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("finduser")
    public String findUser() {
        return JSON.toJSONString(userService.findAll());
    }


}
