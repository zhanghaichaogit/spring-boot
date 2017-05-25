package com.redis.sentinel.web;

import com.redis.sentinel.mq.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private Sender sender;

    @ResponseBody
    @GetMapping("/send")
    public String send(HttpServletRequest request, String msg) {

        sender.send();

        return "Send OK.";

    }


}
