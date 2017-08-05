package com.admin.weixin.test;

import com.admin.weixin.SimpleApplication;
import com.admin.weixin.entity.wx.WxJssdkEntity;
import com.admin.weixin.entity.wx.WxParBtnEntity;
import com.admin.weixin.entity.wx.WxTopBtnEntity;
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

  @Test
  public void jssdkTest() {
    WxJssdkEntity wxJssdkEntity = WxUtil.WxJssdkSign("asdfsafsadf");
    System.out.println(wxJssdkEntity.toString());
  }


  @Test
  public void button(){
    WxParBtnEntity wxParBtnEntity = new WxParBtnEntity();
    WxTopBtnEntity wxTopBtnEntity = new WxTopBtnEntity();
    wxTopBtnEntity.setName("jssdk");
    wxTopBtnEntity.setType("view");
    wxTopBtnEntity.setUrl("http://f5dc90de.ngrok.io/v/weixin/jssdk.html");

    WxTopBtnEntity wxTopBtnEntity2 = new WxTopBtnEntity();
    wxTopBtnEntity2.setName("用户信息3");
    wxTopBtnEntity2.setType("view");
    wxTopBtnEntity2.setUrl(WxUtil.getOauthUrl("http://f5dc90de.ngrok.io/v/weixin/userInfo.html"));
    List<WxTopBtnEntity> wxTopBtnEntityList = new ArrayList<>(0);
    wxTopBtnEntityList.add(wxTopBtnEntity);
    wxTopBtnEntityList.add(wxTopBtnEntity2);
    wxParBtnEntity.setButton(wxTopBtnEntityList);
    WxUtil.menueCreate(wxParBtnEntity);
  }

}
