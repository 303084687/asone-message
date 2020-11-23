package com.ctgtmo.sshr.modules.ums.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.modules.ums.dto.EmailParam;
import com.ctgtmo.sshr.modules.ums.model.wx.Data;
import com.ctgtmo.sshr.modules.ums.service.MessageService;

/**
 * @Title: MessageController.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.controller   
 * @Description: 微信、邮件http对外api
 * @author: 王共亮     
 * @date: 2020年11月20日 上午10:25:16
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/message")
public class MessageController {

  //注入service
  @Autowired
  private MessageService messageService;

  /**
  * @Title: sendTemplateMessage 
  * @Description: 微信发送消息模板
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年11月2日 下午3:48:43
  */
  @ResponseBody
  @RequestMapping(value = "/sendMessage/{templateId}/{touser}", method = RequestMethod.POST)
  public CommonResult sendTemplateMessage(@PathVariable String templateId, @PathVariable String touser, @RequestBody Data data) {
    return messageService.sendTemplateMessage(templateId, touser, data);
  }

  /**
  * @Title: sendTemplateMessage 
  * @Description: 微信发送消息模板-以map方式传值
  * @param map 传值参数其中data(模板数据格式类型){"first":{"color":"#D2691E","value":"预警测试"},"keyword1":{"color":"#FF0000","value":"测试"}}
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年11月3日 上午10:20:03
  */
  @ResponseBody
  @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
  public CommonResult sendTemplateMessage(@RequestBody Map<String, Object> map) {
    return messageService.sendTemplateMessage(map);
  }

  /**
  * @Title: sendEmailTemplate 
  * @Description: 发送邮件模板消息
  * @param emailParam
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年11月20日 下午5:11:49
   */
  @ResponseBody
  @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
  public CommonResult sendEmailTemplate(@RequestBody EmailParam emailParam) {
    return messageService.sendTemplateEmail(emailParam);
  }
}
