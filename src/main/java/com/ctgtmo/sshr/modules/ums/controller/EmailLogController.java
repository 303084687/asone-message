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
import com.ctgtmo.sshr.modules.ums.model.email.EmailLog;
import com.ctgtmo.sshr.modules.ums.service.EmailLogService;

/**
 * @Title: WxMessageLogController.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.controller   
 * @Description: 邮件发送日志api
 * @author: 王共亮     
 * @date: 2020年11月23日 下午2:01:59
 */
@Controller
@RequestMapping("/emailLog")
public class EmailLogController {

  @Autowired
  private EmailLogService logService;

  /** 
   * @Title: list 
   * @Description: 查询日志列表
   * @param templateName 模板名称
   * @param receiveMail 收件人邮箱
   * @param subject 邮件主题
   * @param status 状态0成功1失败
   * @param pageSize 每页显示的数量
   * @param pageNum 当前页码
   * @author 王共亮
   * @date 2020年11月23日 下午2:08:59
   */
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public CommonResult<CommonPage<EmailLog>> list(@RequestParam(value = "templateName", required = false) String templateName,
  @RequestParam(value = "receiveMail", required = false) String receiveMail, @RequestParam(value = "subject", required = false) String subject,
  @RequestParam(value = "status", required = false) int status, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
    Page<EmailLog> logList = logService.list(templateName, receiveMail, subject, status, pageNum, pageSize);
    return CommonResult.success(CommonPage.restPage(logList));
  }

}
