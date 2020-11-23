package com.ctgtmo.sshr.modules.ums.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

/**  
 * @Title: EmailParam.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.dto   
 * @Description: email请求实体参数类
 * @author: 王共亮     
 * @date: 2020年11月20日 下午2:34:31   
 */
public class EmailParam {
  //收件人地址,多个用英文,隔开
  @NotBlank
  private String toMails;

  //抄送人列表,多个用英文,隔开
  private String ccMails;

  //邮件模板id
  private String templateId;

  //邮件内容,优先使用传递的值,若为空使用设置的邮件内容
  private String content;

  //邮件附件
  private List<HashMap<String, Object>> fileList;

  //模板参数对应的值
  private Map<String, Object> templateValue;

  public String getToMails() {
    return toMails;
  }

  public void setToMails(String toMails) {
    this.toMails = toMails;
  }

  public String getCcMails() {
    return ccMails;
  }

  public void setCcMails(String ccMails) {
    this.ccMails = ccMails;
  }

  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<HashMap<String, Object>> getFileList() {
    return fileList;
  }

  public void setFileList(List<HashMap<String, Object>> fileList) {
    this.fileList = fileList;
  }

  public Map<String, Object> getTemplateValue() {
    return templateValue;
  }

  public void setTemplateValue(Map<String, Object> templateValue) {
    this.templateValue = templateValue;
  }

}
