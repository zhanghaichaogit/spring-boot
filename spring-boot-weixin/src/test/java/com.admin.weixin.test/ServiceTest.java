package com.admin.weixin.test;

import com.admin.weixin.SimpleApplication;
import com.admin.weixin.entity.wx.WxJssdkEntity;
import com.admin.weixin.util.weixin.util.WxUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhanghaichao on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleApplication.class)
public class ServiceTest {

  @Test
  public void jssdkTest() {
    WxJssdkEntity wxJssdkEntity = WxUtil.getJsapiTicket("asdfsafsadf");
    System.out.println(wxJssdkEntity.toString());
  }

}
