package com.admin.weixin.web;

import com.admin.weixin.constant.HttpCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/v/weixin")
public class IndexController {

  /**
   * jssdk测试页面
   *
   * @param request
   * @return HTML页面
   */
  @RequestMapping(value = "/jssdk.html", method = RequestMethod.GET, produces = HttpCode.HTML)
  public String index(HttpServletRequest request, Model model) {
    return "weixin/jssdk/jssdk";
  }

}
