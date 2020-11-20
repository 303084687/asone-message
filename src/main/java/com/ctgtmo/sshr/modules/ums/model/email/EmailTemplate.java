package com.ctgtmo.sshr.modules.ums.model.email;

import java.util.Date;

/**  
 * @Title: EmailTemplate.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.model.email   
 * @Description: 邮件配置实体类
 * @author: 王共亮     
 * @date: 2020年11月19日 下午5:17:05   
 */
public class EmailTemplate {
  //主键
  private int id;

  //模板id
  private String templateId;

  //模板名称
  private String templateName;

  //邮件服务地址
  private String host;

  //邮件服务端口号
  private String port;

  //邮件标题
  private String subject;

  //邮件服务器用户名
  private String userName;

  //邮件服务器密码
  private String passWord;

  //发件人地址
  private String fromMail;

  //邮件内容
  private String content;

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

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String getFromMail() {
    return fromMail;
  }

  public void setFromMail(String fromMail) {
    this.fromMail = fromMail;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
