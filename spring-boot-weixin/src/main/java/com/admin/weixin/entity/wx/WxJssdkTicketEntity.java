package com.admin.weixin.entity.wx;

import com.admin.weixin.entity.BaseEntity;

/**
 * 生成JS-SDK权限验证的签名 返回值
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115"></a>
 * @author zhanghaichao on 2017/8/3.
 */
public class WxJssdkTicketEntity extends BaseEntity {
  private static final long serialVersionUID = 8452478519242170469L;

  /**
   * 返回码
   */
  private Integer errcode;

  /**
   * 错误信息
   */
  private String errmsg;

  /**
   * jsapi_ticket签名
   */
  private String ticket;

  /**
   * 过期时间
   */
  private String expires_in;

  public Integer getErrcode() {
    return errcode;
  }

  public void setErrcode(Integer errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public String getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(String expires_in) {
    this.expires_in = expires_in;
  }
}
