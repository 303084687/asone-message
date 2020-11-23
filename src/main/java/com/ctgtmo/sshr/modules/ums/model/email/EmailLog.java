package com.ctgtmo.sshr.modules.ums.model.email;

import java.util.Date;

/**
 * @Title: EmailLog.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.model.email   
 * @Description: 邮件发送日志
 * @author: 王共亮     
 * @date: 2020年11月23日 上午10:37:34
 */
public class EmailLog {
  //主键
  private int id;

  //模板主键
  private String templateId;

  //模板名称
  private String templateName;

  //邮件主体
  private String subject;

  //邮件收件人
  private String receiveMail;

  //邮件内容
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

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getReceiveMail() {
    return receiveMail;
  }

  public void setReceiveMail(String receiveMail) {
    this.receiveMail = receiveMail;
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
