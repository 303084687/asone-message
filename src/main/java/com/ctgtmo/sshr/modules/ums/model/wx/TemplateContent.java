package com.ctgtmo.sshr.modules.ums.model.wx;

/**  
 * @Title: TemplateContent.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.entity   
 * @Description: 推送的微信公众信息的每一个 {{}} 值的内容和颜色封装
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:45:25   
 */
public class TemplateContent {
  /**
   * 消息
   */
  private String value;

  /**
   * 消息颜色
   */
  private String color;

  public TemplateContent(String value) {
    this.value = value;
    this.color = "#173177";
  }

  public TemplateContent(String value, String color) {
    this.value = value;
    this.color = color;
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
