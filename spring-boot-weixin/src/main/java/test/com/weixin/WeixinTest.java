package test.com.weixin;

import com.alibaba.fastjson.JSONObject;
import com.redis.weixin.SimpleApplication;
import com.redis.weixin.entity.wx.WxParBtnEntity;
import com.redis.weixin.entity.wx.WxTopBtnEntity;
import com.redis.weixin.util.weixin.WeixinApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghaichao on 2017/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleApplication.class)
@WebAppConfiguration
public class WeixinTest {
    private static final String appid = "wxbdfdac829ca8f1ff";
    private static final String secret = "7d7a2817d7717577a2bf64c6ca943a55";


    //自定义菜单
    @Test
    public void menueCreate() {
        WxParBtnEntity wxParBtnEntity = new WxParBtnEntity();
        WxTopBtnEntity wxTopBtnEntity1 = new WxTopBtnEntity();
        WxTopBtnEntity wxTopBtnEntity2 = new WxTopBtnEntity();
        List<WxTopBtnEntity> wxTopBtnEntityList = new ArrayList<>(0);
        wxTopBtnEntity1.setName("第一个");
        wxTopBtnEntity1.setType("click");
        wxTopBtnEntity1.setKey("123213123");
        wxTopBtnEntity2.setName("第二个");
        wxTopBtnEntity2.setType("click");
        wxTopBtnEntity2.setKey("123213123");
        wxTopBtnEntityList.add(wxTopBtnEntity1);
        wxTopBtnEntityList.add(wxTopBtnEntity2);
        wxParBtnEntity.setButton(wxTopBtnEntityList);

        String json = JSONObject.toJSONString(wxParBtnEntity);
        String result = WeixinApi.menueCreate(json);
        System.out.println(result);
    }

}
