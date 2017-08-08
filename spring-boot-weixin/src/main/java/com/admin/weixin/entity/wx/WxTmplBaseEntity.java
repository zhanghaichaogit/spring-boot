package com.admin.weixin.entity.wx;

import com.admin.weixin.entity.BaseEntity;

import java.util.List;

/**
 * 微信推送模板基础参数
 * @author zhanghaichao on 2017/8/8.
 */
public class WxTmplBaseEntity extends BaseEntity {
  private static final long serialVersionUID = -804026057208608297L;

  private String touser;

  private String template_id;

  private String url;

  private String topcolor;

  private List<WxTmplDataEntity> data;

  public String getTouser() {
    return touser;
  }

  public void setTouser(String touser) {
    this.touser = touser;
  }

  public String getTemplate_id() {
    return template_id;
  }

  public void setTemplate_id(String template_id) {
    this.template_id = template_id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTopcolor() {
    return topcolor;
  }

  public void setTopcolor(String topcolor) {
    this.topcolor = topcolor;
  }

  public List<WxTmplDataEntity> getData() {
    return data;
  }

  public void setData(List<WxTmplDataEntity> data) {
    this.data = data;
  }

  public String toJSON() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("{");
    buffer.append(String.format("\"touser\":\"%s\"", this.getTouser())).append(",");
    buffer.append(String.format("\"template_id\":\"%s\"", this.getTemplate_id())).append(",");
    buffer.append(String.format("\"url\":\"%s\"", this.url)).append(",");
    buffer.append(String.format("\"topcolor\":\"%s\"", this.getTopcolor())).append(",");
    buffer.append("\"data\":{");
    for (int i = 0; i < this.data.size(); i++) {
      WxTmplDataEntity param = data.get(i);
      // 判断是否追加逗号
      if (i < this.data.size() - 1) {

        buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(), param.getValue(), param.getColor()));
      } else {
        buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(), param.getValue(), param.getColor()));
      }
    }
    buffer.append("}");
    buffer.append("}");
    return buffer.toString();
  }
}
