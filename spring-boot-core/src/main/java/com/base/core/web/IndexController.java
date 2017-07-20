package com.base.core.web;

import com.alibaba.fastjson.JSON;
import com.base.core.service.UserService;
import com.pro.base.controller.WebBaseController;
import com.pro.base.target.UserPermissions;
import com.pro.base.util.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class IndexController extends WebBaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("finduser")
    public String findUser() {
//        return JSON.toJSONString(userService.findAll());
        return JSON.toJSONString(userService.findAllT());
    }

    /**
     * 查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("finduser2")
    public String findUser2() {
        return JSON.toJSONString(userService.findAll());
    }


    /**
     * 数据库插入测试
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("uid")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        String[] role = new String[]{"wx-user-show", "wx-user-pwd"};
        session.setAttribute("userRole", role);
        session.setAttribute("uid", uid);
        return session.getId();
    }

    @RequestMapping(value = {"", "/", "index"})
    public String index() {
        return "index2";
    }

    /**
     * redis 测试 这个是默认连接一个redis服务的情况
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("jedis/set")
    public String jedisTest() {
        stringRedisTemplate.opsForValue().set("key", "value", 200, TimeUnit.SECONDS);
        return "abc";
    }

    /**
     * 日志测试
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logtest.api")
    public String logtest() {
        LOGGER.error("12312321");
        return "hello";
    }

    /**
     * 自定义注解测试
     */
    @ResponseBody
    @RequestMapping(value = "permission.api")
    @UserPermissions(value = "wx-user-show")
    public String userPermission() {
        return "userPermission";
    }

    /**
     * 页面测试类
     */
    @RequestMapping(value = "viewShow", method = {RequestMethod.GET}, produces = BaseUtil.HTML)
    public String viewShow(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "asdfasfsafasdf");
        return "web";
    }

    @ResponseBody
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message_e"));
        return map;
    }


    /**
     * 页面测试类
     */
    @RequestMapping(value = "coreIndex", method = {RequestMethod.GET}, produces = BaseUtil.HTML)
    public String coreIndex(Map<String, Object> model) {
        model.put("message_e", "aaaaaaaaaaaa");
        return "index";
    }

}
