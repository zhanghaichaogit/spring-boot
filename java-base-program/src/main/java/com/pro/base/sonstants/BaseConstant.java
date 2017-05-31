package com.pro.base.sonstants;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * 基础相关常量
 *
 * @author BBF
 */
public interface BaseConstant {
    /**
     * ASCII : 通用变量
     */
    static String US_ASCII = "US-ASCII";
    /**
     * US_ASCII : 字符集
     */
    Charset CHARSET_US_ASCII = Charset.forName(US_ASCII);
    /**
     * UTF8 : 通用变量
     */
    String UTF8 = "UTF-8";
    /**
     * UTF8 : 字符集
     */
    Charset CHARSET_UTF8 = Charset.forName(UTF8);
    /**
     * GBK : 通用变量
     */
    String GBK = "GBK";
    /**
     * GBK : 字符集
     */
    Charset CHARSET_GBK = Charset.forName(GBK);
    /**
     * ISO859-1 : 通用变量
     */
    String ISO859 = "ISO-8859-1";
    /**
     * ISO859-1 : 字符集
     */
    Charset CHARSET_ISO859 = Charset.forName(ISO859);
    /**
     * UNICODE : 通用变量
     */
    String UNICODE = "UNICODE";
    /**
     * UNICODE : 字符集
     */
    Charset CHARSET_UNICODE = Charset.forName(UNICODE);
    /**
     * HTML : HTML的ContendType类型
     */
    String HTML = "text/html;charset=UTF-8";
    /**
     * JSON : JSON文件的ContendType类型
     */
    String JSON = "application/json;charset=UTF-8";

    /**
     * BASE62 : 数字和大小写字母
     */
    String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * BASE62_ARR : 数字和大小写字母
     */
    char[] BASE62_ARR = BASE62.toCharArray();

    /**
     * HTTP200 : HTTP Status = 200
     */
    int HTTP200 = HttpServletResponse.SC_OK;
    /**
     * HTTP401 : HTTP Status = 400
     */
    int HTTP400 = HttpServletResponse.SC_BAD_REQUEST;
    /**
     * HTTP401 : HTTP Status = 401
     */
    int HTTP401 = HttpServletResponse.SC_UNAUTHORIZED;
    /**
     * HTTP401 : HTTP Status = 403
     */
    int HTTP403 = HttpServletResponse.SC_FORBIDDEN;
    /**
     * HTTP500 : HTTP Status = 500
     */
    int HTTP500 = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    /**
     * 字符串：“0”
     */
    String ZERO = "0";

}
