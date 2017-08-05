package com.admin.weixin.util.code;

import java.util.Formatter;

/**
 * @author zhanghaichao on 2017/8/3.
 */
public class SHA {

  public static String byteToHex(final byte[] hash) {
    Formatter formatter = new Formatter();
    for (byte b : hash)
    {
      formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
  }

}
