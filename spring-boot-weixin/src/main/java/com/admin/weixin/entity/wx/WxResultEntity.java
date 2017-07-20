package com.admin.weixin.entity.wx;

import com.admin.weixin.entity.BaseEntity;

/**
 * @author  zhanghaichao on 2017/7/20.
 */
public class WxResultEntity extends BaseEntity {
  private static final long serialVersionUID = -4534964696712028830L;

  /**
   * 错误代码
   */
  private Integer errcode;
  /**
   * 返回的错误信息
   */
  private String errmsg;
  /**
   * 转化的错误信息
   */
  private String message;

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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
