package com.admin.weixin.entity.wx;

import com.admin.weixin.entity.BaseEntity;

/**
 * 微信tmpl data
 * @author zhanghaichao on 2017/8/8.
 */
public class WxTmplDataEntity extends BaseEntity {
  private static final long serialVersionUID = 1300024466396178146L;

  // 参数名称
  private String name;

  private String value;

  private String color;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
