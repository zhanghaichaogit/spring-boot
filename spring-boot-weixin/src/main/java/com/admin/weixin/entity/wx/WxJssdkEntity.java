package com.admin.weixin.entity.wx;

import com.admin.weixin.entity.BaseEntity;

/**
 * 返回页面的jssdk参数
 *@author zhanghaichao on 2017/8/3.
 */
public class WxJssdkEntity extends BaseEntity {
  private static final long serialVersionUID = -7248032447014725216L;

  private String appId;

  private String timestamp;

  private String nonceStr;

  private String signature;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
}
