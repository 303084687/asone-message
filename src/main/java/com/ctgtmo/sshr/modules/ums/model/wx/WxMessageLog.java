package com.ctgtmo.sshr.modules.ums.model.wx;

import java.util.Date;

/**  
 * @Title: WxMessageLog.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.model.wx   
 * @Description: 
 * @author: 王共亮     
 * @date: 2020年10月29日 上午11:33:43   
 */
public class WxMessageLog {
  //主键
  private int id;

  //模板主键
  private String templateId;

  //模板名称
  private String templateName;

  //微信接收者
  private String touser;

  //发送参数
  private String content;

  //0成功1失败
  private int status;

  //失败/成功原因
  private String message;

  //创建时间
  private Date createTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public String getTouser() {
    return touser;
  }

  public void setTouser(String touser) {
    this.touser = touser;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
