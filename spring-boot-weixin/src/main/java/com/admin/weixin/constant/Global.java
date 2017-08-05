package com.admin.weixin.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件里的参数
 * @author  zhanghaichao on 2017/8/5.
 */
@Component
public class Global{

    @Value("${pro.web.admin}")
    private String webAdmin;

    public String getWebAdmin() {
        return webAdmin;
    }
}
