package com.ctgtmo.sshr.modules.ums.model.wx;

/**  
 * @Title: TemplateMessage.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.ssg.model   
 * @Description: 消息模板实体
 * @author: 王共亮     
 * @date: 2020年10月22日 下午3:02:25   
 */
public class TemplateMessage {
  //用户openid,必传
  private String touser;

  //模板消息ID,必传
  private String template_id;

  //详情跳转页面--请注意，URL置空，则在发送后，点击模板消息无法点击
  private String url;

  /**
   * 跳小程序所需数据，不需跳小程序可不用传该数据
   * url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。
   * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url
   */
  private Miniprogram miniprogram;

  //模板数据封装实体--参数对应模板中的key,必传
  private Object data;

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

  public Miniprogram getMiniprogram() {
    return miniprogram;
  }

  public void setMiniprogram(Miniprogram miniprogram) {
    this.miniprogram = miniprogram;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

}
