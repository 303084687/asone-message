package com.ctgtmo.sshr.modules.ums.controller;

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
import com.ctgtmo.sshr.modules.ums.model.email.EmailTemplate;
import com.ctgtmo.sshr.modules.ums.service.EmailTemplateService;

/**
 * @Title: EmailTemplateController.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.controller   
 * @Description:邮件模板controller 
 * @author: 王共亮     
 * @date: 2020年11月20日 上午10:32:45
 */
@Controller
@RequestMapping("/email")
@SuppressWarnings("all")
public class EmailTemplateController {

  @Autowired
  private EmailTemplateService templateService;

  /**
  * @Title: create 
  * @Description: 新增邮件模板配置
  * @param emailTemplate
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年11月20日 下午2:45:02
   */
  @ResponseBody
  @RequestMapping(value = "/template/create", method = RequestMethod.POST)
  public CommonResult create(@Valid @RequestBody EmailTemplate emailTemplate) {
    boolean success = templateService.addEmailTemplate(emailTemplate);
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  /**
  * @Title: update 
  * @Description: 修改邮件模板配置
  * @param id 模板主键
  * @param emailTemplate
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年11月20日 下午2:48:04
   */
  @ResponseBody
  @RequestMapping(value = "/template/update/{id}", method = RequestMethod.POST)
  public CommonResult update(@PathVariable int id, @RequestBody EmailTemplate emailTemplate) {
    boolean success = templateService.updateEmailTemplate(id, emailTemplate);
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  /**
  * @Title: delete 
  * @Description: 删除邮件模板消息配置
  * @param templateId 模板主键
  * @return CommonResult  
  * @author 王共亮
  * @date 2020年10月29日 下午2:57:33
   */
  @ResponseBody
  @RequestMapping(value = "/template/delete/{id}", method = RequestMethod.POST)
  public CommonResult delete(@PathVariable int id) {
    boolean success = templateService.deleteEmailTemplate(id);
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
  @RequestMapping(value = "/template/list", method = RequestMethod.GET)
  public CommonResult<CommonPage<EmailTemplate>> list(@RequestParam(value = "templateId", required = false) String templateId,
  @RequestParam(value = "templateName", required = false) String templateName, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
    Page<EmailTemplate> templateList = templateService.queryEmailTemplateList(templateId, templateName, pageNum, pageSize);
    return CommonResult.success(CommonPage.restPage(templateList));
  }
}
