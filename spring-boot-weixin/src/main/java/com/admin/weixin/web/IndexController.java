package com.admin.weixin.web;

import com.admin.weixin.constant.Global;
import com.admin.weixin.constant.HttpCode;
import com.admin.weixin.entity.wx.WxJssdkEntity;
import com.admin.weixin.entity.wx.WxUserInfoEntity;
import com.admin.weixin.util.json.JsonUtil;
import com.admin.weixin.util.weixin.util.WxUtil;
import com.admin.weixin.util.xml.XmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/v/weixin")
public class IndexController {
  @Resource
  private Global global;

  /**
   * jssdk测试页面
   *
   * @param request request
   * @return HTML页面
   */
  @RequestMapping(value = "/jssdk.html", method = RequestMethod.GET, produces = HttpCode.HTML)
  public String jssdk(HttpServletRequest request, Model model) {
    String url = global.getWebAdmin();
    url += "/v/weixin/jssdk.html";
    WxJssdkEntity wxJssdkEntity = WxUtil.WxJssdkSign(url);
    model.addAttribute("sign", wxJssdkEntity);
    return "weixin/jssdk/jssdk";
  }

  /**
   * 获取用户基本信息
   * @param request request
   * @param code 用户code
   * @return HTML页面
   */
  @ResponseBody
  @RequestMapping(value = "/userInfo.html", method = RequestMethod.GET)
  public String userInfo(HttpServletRequest request, String code) {
    WxUserInfoEntity wxUserInfoEntity = WxUtil.getUserInfo(code);
    String userInfo = JsonUtil.toJSONString(wxUserInfoEntity);
    return userInfo;
  }

  /**
   * 获取微信xml信息
   * @param request request
   * @return 消息处理
   */
  @ResponseBody
  @RequestMapping(value = "/processRequestMsg.json")
  public String processRequestMsg(HttpServletRequest request) {
    // 将解析结果存储在HashMap中
    Map<String, String> map = new HashMap<String, String>();
    try {
      map = XmlUtil.parseXml(request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(JsonUtil.toJSONString(map));
    return JsonUtil.toJSONString(map);
  }

}
