package com.ctgtmo.sshr.modules.ums.service;

import java.util.Map;

import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.common.exception.ApiException;
import com.ctgtmo.sshr.modules.ums.model.wx.Data;

/**  
 * @Title: WxChatService.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.service   
 * @Description: 消息推送接口
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:49:02   
 */
public interface WxChatService {
  /**
  * @Title: sendTemplateMessage 
  * @Description: 发送微信模板消息
  * @param templateId 模板id
  * @param touser 接收者微信openId
  * @param data 模板数据
  * @throws ApiException JSONObject  
  * @author 王共亮
  * @date 2020年11月2日 下午2:48:53
   */
  CommonResult<?> sendTemplateMessage(String templateId, String touser, Data data) throws ApiException;

  /**
  * @Title: sendTemplateMessage 
  * @Description: 发送微信模板消息--以map方式传值
  * @param map
  * @throws ApiException CommonResult<?>  
  * @author 王共亮
  * @date 2020年11月3日 上午10:01:55
   */
  CommonResult<?> sendTemplateMessage(Map<String, Object> map) throws ApiException;
}
