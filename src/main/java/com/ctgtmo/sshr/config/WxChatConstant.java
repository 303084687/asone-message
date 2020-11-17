package com.ctgtmo.sshr.config;

/**  
 * @Title: WxChatConstant.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.constant   
 * @Description: 微信公众号常量配置
 * @author: 王共亮     
 * @date: 2020年10月22日 上午9:57:33   
 */
public interface WxChatConstant {
  /**
   * 微信公众号相关操作的链接
   */
  interface Url {

    /**
     * 1、获取 accessToken 的请求地址
     * 参数1: APPID
     * 参数2: APPSECRET
     */
    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 2、发送模版信息的url
     * 参数1: ACCESS_TOKEN
     */
    String SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
  }
}
