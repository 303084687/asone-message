package com.ctgtmo.sshr.modules.ums.model.wx;

/**  
 * @Title: Miniprogram.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.model   
 * @Description: 小程序参数实体
 * @author: 王共亮     
 * @date: 2020年10月26日 上午9:39:19   
 */
public class Miniprogram {
  //所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）跳转小程序是必传参数
  private String appid;

  //所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏,跳转小程序不是必传参数
  private String pagepath;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getPagepath() {
    return pagepath;
  }

  public void setPagepath(String pagepath) {
    this.pagepath = pagepath;
  }

}
