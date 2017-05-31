package com.pro.base.controller;

import com.pro.base.util.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ErrorController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    /**
     * 404方法
     */
    @RequestMapping(value = "404", method = {RequestMethod.GET}, produces = BaseUtil.HTML)
    public String errorNotFound(Map<String, Object> model) {
        model.put("message", "访问链接不存在");
        return "error_404";
    }
}
