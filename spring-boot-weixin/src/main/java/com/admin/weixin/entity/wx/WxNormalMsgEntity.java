package com.admin.weixin.entity.wx;

import com.admin.weixin.entity.BaseEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhanghaichao on 2017/7/20.
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140453"></a>
 */
@XmlRootElement
public class WxNormalMsgEntity extends BaseEntity {
  //TODO 这里的参数还没有写全 看https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140453后面接着补全
  private static final long serialVersionUID = -7654475045505209439L;
  /**
   * 开发者微信号
   */
  private String ToUserName;

  /**
   *  发送方帐号（一个OpenID）
   */
  private String FromUserName;
  /**
   * 消息创建时间 （整型）
   */
  private Integer CreateTime;
  /**
   *  text img voice
   */
  private String MsgType;

  /**
   *  文本消息内容
   */
  private String Content;
  /**
   * 消息id，64位整型
   */
  private Integer MsgId;

  /**
   * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
   */
  private String MediaId;

  /**
   * 语音格式
   */
  private String Format;

  /**
   * 语音识别结果，UTF8编码
   */
  private String Recognition;

  public String getToUserName() {
    return ToUserName;
  }

  public void setToUserName(String toUserName) {
    ToUserName = toUserName;
  }

  public String getFromUserName() {
    return FromUserName;
  }

  public void setFromUserName(String fromUserName) {
    FromUserName = fromUserName;
  }

  public Integer getCreateTime() {
    return CreateTime;
  }

  public void setCreateTime(Integer createTime) {
    CreateTime = createTime;
  }

  public String getMsgType() {
    return MsgType;
  }

  public void setMsgType(String msgType) {
    MsgType = msgType;
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }

  public Integer getMsgId() {
    return MsgId;
  }

  public void setMsgId(Integer msgId) {
    MsgId = msgId;
  }

  public String getMediaId() {
    return MediaId;
  }

  public void setMediaId(String mediaId) {
    MediaId = mediaId;
  }

  public String getFormat() {
    return Format;
  }

  public void setFormat(String format) {
    Format = format;
  }

  public String getRecognition() {
    return Recognition;
  }

  public void setRecognition(String recognition) {
    Recognition = recognition;
  }

}
