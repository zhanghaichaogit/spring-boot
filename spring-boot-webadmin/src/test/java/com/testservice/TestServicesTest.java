package com.testservice;

import com.admin.SimpleApplication;
import com.admin.util.http.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
//指定SpringBoot工程的Application启动类
@SpringBootTest(classes = SimpleApplication.class)
//Spring boot用来模拟ServletContext，并加载上下文
@WebAppConfiguration
public class TestServicesTest {
  @Test
  public void testOne() {
    String appID = "wxbdfdac829ca8f1ff";
    String appsecret = "7d7a2817d7717577a2bf64c6ca943a55";
    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential2&appid=" + appID + "&secret=" + appsecret;
    try {
      String result = HttpUtil.get(url);
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}