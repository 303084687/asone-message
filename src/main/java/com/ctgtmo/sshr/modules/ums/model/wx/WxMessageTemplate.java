package com.ctgtmo.sshr.modules.ums.model.wx;

import java.util.Date;

import javax.validation.constraints.NotBlank;

/**  
 * @Title: WxMessageTemplate.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.model.wx   
 * @Description: 微信模板配置实体类
 * @author: 王共亮     
 * @date: 2020年10月29日 上午11:27:49   
 */
public class WxMessageTemplate {
  private int id;

  //模板Id
  @NotBlank
  private String templateId;

  //模板名称
  @NotBlank
  private String templateName;

  //微信公众号appid
  @NotBlank
  private String appId;

  //微信公众号secret
  @NotBlank
  private String secret;

  //微信公众号设置的token
  private String token;

  //微信公众号设置的aesKey
  private String aesKey;

  //详情跳转页面--请注意，URL置空，则在发送后，点击模板消息无法点击
  private String url;

  //公众号关联小程序appid
  private String miniAppId;

  //公众号关联小程序跳转地址带参数
  private String miniPagePath;

  //描述
  private String description;

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

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAesKey() {
    return aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMiniAppId() {
    return miniAppId;
  }

  public void setMiniAppId(String miniAppId) {
    this.miniAppId = miniAppId;
  }

  public String getMiniPagePath() {
    return miniPagePath;
  }

  public void setMiniPagePath(String miniPagePath) {
    this.miniPagePath = miniPagePath;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
