package com.admin.weixin.test;

import com.admin.weixin.SimpleApplication;
import com.admin.weixin.entity.wx.WxJssdkEntity;
import com.admin.weixin.entity.wx.WxParBtnEntity;
import com.admin.weixin.entity.wx.WxResultEntity;
import com.admin.weixin.entity.wx.WxTmplBaseEntity;
import com.admin.weixin.entity.wx.WxTmplDataEntity;
import com.admin.weixin.entity.wx.WxTockenEntity;
import com.admin.weixin.entity.wx.WxTopBtnEntity;
import com.admin.weixin.util.json.JsonUtil;
import com.admin.weixin.util.weixin.util.WxUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghaichao on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleApplication.class)
public class ServiceTest {

  //TODO 修改appid和secret的方法
  private static final String appid = "wxbdfdac829ca8f1ff";
  private static final String secret = "7d7a2817d7717577a2bf64c6ca943a55";
//  private static final String appid = "wx7e3a2fb8a60efdf8";
//  private static final String secret = "3dbe421ffcea5da8ea8496b160537dbb";

  @Test
  public void jssdkTest() {
    WxJssdkEntity wxJssdkEntity = WxUtil.WxJssdkSign("asdfsafsadf", appid, secret);
    System.out.println(wxJssdkEntity.toString());
  }

  @Test
  public void button() {
    WxParBtnEntity wxParBtnEntity = new WxParBtnEntity();
    WxTopBtnEntity wxTopBtnEntity = new WxTopBtnEntity();
    wxTopBtnEntity.setName("jssdk");
    wxTopBtnEntity.setType("view");
    wxTopBtnEntity.setUrl("http://56b5e914.ngrok.io/v/weixin/jssdk.html");

    WxTopBtnEntity wxTopBtnEntity2 = new WxTopBtnEntity();
    wxTopBtnEntity2.setName("用户信息3");
    wxTopBtnEntity2.setType("view");
    wxTopBtnEntity2.setUrl(WxUtil.getOauthUrl("http://56b5e914.ngrok.io/v/weixin/userInfo.html", appid, secret));

    WxTopBtnEntity wxTopBtnEntity3 = new WxTopBtnEntity();
    wxTopBtnEntity3.setName("曾一卡");
    wxTopBtnEntity3.setType("view");
    String url = WxUtil.getOauthUrl("http://schp.tiantianzaixian.net/OneCardSolution/parentPay?state=zengzenghaha曾", appid, "");
    System.out.println(url);
    wxTopBtnEntity3.setUrl(url);
    List<WxTopBtnEntity> wxTopBtnEntityList = new ArrayList<>(0);
//    wxTopBtnEntityList.add(wxTopBtnEntity);
//    wxTopBtnEntityList.add(wxTopBtnEntity2);
    wxTopBtnEntityList.add(wxTopBtnEntity3);
    wxParBtnEntity.setButton(wxTopBtnEntityList);
    WxResultEntity resultEntity = WxUtil.menueCreate(wxParBtnEntity, appid, secret);
    System.out.println(JsonUtil.toJSONString(resultEntity));
  }

  @Test
  public void sendMsg() {
    WxTmplBaseEntity wxTmplBaseEntity = new WxTmplBaseEntity();
    wxTmplBaseEntity.setTemplate_id("6bnY4D51iGEMbG8FXeAneiajscalLVhxLlFmEOvsIjw");
    wxTmplBaseEntity.setUrl("http://www.baidu.com");
    wxTmplBaseEntity.setTopcolor("#FF0000");
    wxTmplBaseEntity.setTouser("o4RYAwqtBs1ve4KZoaigLeWIv5c8");

    WxTmplDataEntity wxTmplDataEntity = new WxTmplDataEntity();
    wxTmplDataEntity.setValue("啦啦啦");
    wxTmplDataEntity.setColor("#173177");
    wxTmplDataEntity.setName("title");

    List<WxTmplDataEntity> list = new ArrayList<>();
    list.add(wxTmplDataEntity);
    wxTmplBaseEntity.setData(list);

    String json = wxTmplBaseEntity.toJSON();
    System.out.println(json);
    WxUtil.sendTemplate(wxTmplBaseEntity, appid, secret);
  }

  @Test
  public void getTocken() {
    WxTockenEntity wxTockenEntity = WxUtil.getTocken(appid, secret);
    String json = JsonUtil.toJSONString(wxTockenEntity);
    System.out.println(json);
  }

}
