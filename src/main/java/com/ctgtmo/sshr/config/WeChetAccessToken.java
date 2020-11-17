package com.ctgtmo.sshr.config;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctgtmo.sshr.common.exception.ApiException;
import com.ctgtmo.sshr.utils.HttpRequest;

/**  
 * @Title: SendMessageUtils.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.config   
 * @Description: 获取微信token工具类
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:05:29   
 */
@Component
public class WeChetAccessToken {
  /**
   * 获取 accessToken （token有效期为2小时, 设置缓存, 过期并重新获取 accessToken）使用传值形式
   * 微信请求正确返回 {\"access_token\":\"37_B7bSN7N0VoqOVhf5rOk7NOHY6aMoxvE15VxNIcnD3f2kXvZkc0HOU-9rhfGZyWAoYkVfLrPzxTMhdcf86kgQeabfWSV-DH0hUYD8YMBF9vcbASzwRlEE3zJbKW2PuHJIl5Nu4BLouY4rUSFwTCBbAHAIRQ\",\"expires_in\":7200}
   * 微信请求错误返回 {"errcode":40013,"errmsg":"invalid appid"}
   * 流程：
   * 1、判断token是否过期
   * 2、获取 url 并拼接参数 APPID + APPSECRET
   * 3、发起请求
   * 4、判断请求是否成功
   * 5、缓存accessToken + accessToken过期时间到jvm 内存中
   * @throws ErrorException 
   */
  public String getToken(String appId, String secret) throws ApiException {
    if (WxChatCache.AccessToken.expiration <= System.currentTimeMillis()) {
      String url = WxChatConstant.Url.ACCESS_TOKEN_URL.replace("APPID", appId).replace("APPSECRET", secret);
      //http请求获取token
      String result = HttpRequest.sendGet(url);
      //解析请求结果
      JSONObject jsonObject = JSON.parseObject(result);
      //处理错误的请求并返回错误信息
      Object errcode = jsonObject.get("errcode");
      Object errmsg = jsonObject.get("errmsg");
      if (errcode != null) {
        throw new ApiException(errmsg.toString());
      }
      // 设置为1小时59分钟有效期，防止空指针异常
      WxChatCache.AccessToken.token = jsonObject.get("access_token").toString();
      WxChatCache.AccessToken.expiration = ((Integer.parseInt(jsonObject.get("expires_in").toString()) - 1) * 1000) + System.currentTimeMillis();
    }
    return WxChatCache.AccessToken.token;
  }
}
