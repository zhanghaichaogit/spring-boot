package com.pro.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhanghaichao on 2017/6/19.
 */

@Controller
public class ViewPageController extends WebBaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @Value("${web.url.coreadmin}")
    private String coreadmin;

    @Value("${web.url.adminurl}")
    private String adminUrl;

    /**
     * coreadmin页面转发
     */
    @RequestMapping(value = "coreadmin", method = {RequestMethod.GET})
    public String coreadmin() {
        return "redirect:" + coreadmin;
    }

    /**
     * coreadmin页面转发
     */
    @RequestMapping(value = "adminUrl", method = {RequestMethod.GET})
    public String adminUrl() {
        return "redirect:" + adminUrl;
    }

    /**
     * 测试测试
     */
    @ResponseBody
    @RequestMapping(value = "testtt", method = {RequestMethod.GET})
    public String testtt() {
        return "rrrrrrrrr";
    }
}
