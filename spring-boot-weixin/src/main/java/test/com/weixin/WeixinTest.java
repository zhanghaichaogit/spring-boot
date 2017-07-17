package test.com.weixin;

import com.redis.weixin.SimpleApplication;
import com.redis.weixin.util.weixin.WeixinApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by zhanghaichao on 2017/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleApplication.class)
@WebAppConfiguration
public class WeixinTest {
    private static final String appid = "wxbdfdac829ca8f1ff";
    private static final String secret = "7d7a2817d7717577a2bf64c6ca943a55";

    //获取tocken
    @Test
    public void getTocken() {
        String tocken = "";
        tocken = WeixinApi.getTocken();
        System.out.println(tocken);
    }

    //自定义菜单
    @Test
    public void menueCreate() {
        String json = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
        String result = WeixinApi.menueCreate(json);
        System.out.println(result);
    }

}
