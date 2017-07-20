package com.admin.weixin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class IndexController {
  /**
   * 获取tocken
   *
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/getMessageXml", method = RequestMethod.POST, produces = "application/xml")
  public void getTocken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
    response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；boolean isGet = request.getMethod().toLowerCase().equals("get");
    boolean isGet = request.getMethod().toLowerCase().equals("get");
    PrintWriter out = response.getWriter();
    try {
      if (isGet) {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");//随机字符串

        response.getWriter().write(echostr);
      } else {
        String respMessage = "异常消息！";

        try {
          out.write(respMessage);
        } catch (Exception e) {
        }

      }
    } catch (Exception e) {
    } finally {
      out.close();
    }
  }

}
