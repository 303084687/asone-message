package com.ctgtmo.sshr.modules.ums.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgtmo.sshr.common.api.CommonPage;
import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.modules.ums.model.wx.Data;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageTemplate;
import com.ctgtmo.sshr.modules.ums.service.WxChatService;
import com.ctgtmo.sshr.modules.ums.service.WxMessageTemplateService;

/**
 * @Title: WxMessageMessageController.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.controller   
 * @Description: 微信推送消息模板配置信息
 * @author: 王共亮     
 * @date: 2020年10月29日 下午2:40:36
 */
@Controller
@RequestMapping("/wx")
@SuppressWarnings("all")
public class WxMessageMessageController {

  @Autowired
  private WxMessageTemplateService templateService;

  //微信发送消息
  @Autowired
  private WxChatService wxChatService;

  /**
  * @Title: create 
  * @Description: 新增微信消息模板配置
  * @param messageTemplate
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年10月29日 下午2:45:02
   */
  @ResponseBody
  @RequestMapping(value = "/messageTemplate/create", method = RequestMethod.POST)
  public CommonResult create(@Valid @RequestBody WxMessageTemplate messageTemplate) {
    return templateService.create(messageTemplate);
  }

  /**
  * @Title: update 
  * @Description: 修改微信消息模板配置
  * @param id 模板主键
  * @param umsResource
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年10月29日 下午2:48:04
   */
  @ResponseBody
  @RequestMapping(value = "/messageTemplate/update/{id}", method = RequestMethod.POST)
  public CommonResult update(@PathVariable int id, @RequestBody WxMessageTemplate messageTemplate) {
    boolean success = templateService.update(id, messageTemplate);
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  /**
  * @Title: delete 
  * @Description: 删除微信模板消息配置
  * @param templateId 模板主键
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年10月29日 下午2:57:33
   */
  @ResponseBody
  @RequestMapping(value = "/messageTemplate/delete/{templateId}", method = RequestMethod.POST)
  public CommonResult delete(@PathVariable String templateId) {
    boolean success = templateService.delete(templateId);
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  /**
  * @Title: list 
  * @Description: 分页查询列表数据
  * @param pageSize 当前页码
  * @param pageNum 每页显示的数量
  * @author 王共亮
  * @date 2020年10月29日 下午2:59:08
   */
  @ResponseBody
  @RequestMapping(value = "/messageTemplate/list", method = RequestMethod.GET)
  public CommonResult<CommonPage<WxMessageTemplate>> list(@RequestParam(value = "templateId", required = false) String templateId,
  @RequestParam(value = "templateName", required = false) String templateName, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
    Page<WxMessageTemplate> templateList = templateService.list(templateId, templateName, pageSize, pageNum);
    return CommonResult.success(CommonPage.restPage(templateList));
  }

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
    return wxChatService.sendTemplateMessage(templateId, touser, data);
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
    return wxChatService.sendTemplateMessage(map);
  }
}
