package com.ctgtmo.sshr.modules.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgtmo.sshr.common.api.CommonPage;
import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageLog;
import com.ctgtmo.sshr.modules.ums.service.WxMessageLogService;

/**
 * @Title: WxMessageLogController.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.controller   
 * @Description: 微信模板消息推送日志api
 * @author: 王共亮     
 * @date: 2020年10月29日 下午3:01:59
 */
@Controller
@RequestMapping("/wxlog")
public class WxMessageLogController {

  @Autowired
  private WxMessageLogService logService;

  /** 
   * @Title: list 
   * @Description: 查询日志列表
   * @param templateId 模板id
   * @param touser 微信openId
   * @param status 状态 1成功2失败
   * @param pageSize 当前页码
   * @param pageNum 每页显示的数量
   * @author 王共亮
   * @date 2020年10月29日 下午2:08:59
   */
  @ResponseBody
  @RequestMapping(value = "/messageLog/list", method = RequestMethod.GET)
  public CommonResult<CommonPage<WxMessageLog>> list(@RequestParam(value = "templateId", required = false) String templateId,
  @RequestParam(value = "touser", required = false) String touser, @RequestParam(value = "status", required = false) int status,
  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
    Page<WxMessageLog> logList = logService.list(templateId, touser, status, pageSize, pageNum);
    return CommonResult.success(CommonPage.restPage(logList));
  }

}
