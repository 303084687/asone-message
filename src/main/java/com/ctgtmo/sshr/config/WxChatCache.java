package com.ctgtmo.sshr.config;

/**  
 * @Title: WxChatCache.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.constant   
 * @Description: 微信缓存类
 * @author: 王共亮     
 * @date: 2020年10月22日 上午9:59:01   
 */
public class WxChatCache {
  /**
   * 微信 accessToken缓存
   */
  public static class AccessToken {
    public static String token = null; // accessToken

    public static Long expiration = 0L; // accessToken 过期时间(获取的token 默认有效期2小时）
  }
}
