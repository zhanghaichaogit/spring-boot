package com.redis.sentinel.web;

import com.pro.base.model.StringRedisModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IndexController {
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Resource
    StringRedisModel stringRedisModel;
    private static final String keyTemplete = "redis:boot:sentinel:key:%s";

    /**
     * redis主从测试链接
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "redis/set/{key}/{msg}", method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public String redissentinel(@PathVariable(name = "key") String key,
                                @PathVariable(name = "msg") String msg) {
        String _key = String.format(keyTemplete, key);

//        StringRedisModel stringRedisModel = new StringRedisModel();
        stringRedisModel.set(_key, msg, 600);
        return _key;
    }

}
