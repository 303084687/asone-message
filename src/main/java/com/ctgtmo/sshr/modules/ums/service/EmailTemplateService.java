package com.ctgtmo.sshr.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgtmo.sshr.modules.ums.model.email.EmailTemplate;

/**
 * @Title: EmailTemplateService.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.service   
 * @Description: 邮件模板配置service
 * @author: 王共亮     
 * @date: 2020年11月19日 下午5:31:34
 */
public interface EmailTemplateService extends IService<EmailTemplate> {
  /**
  * @Title: addEmailTemplate 
  * @Description: 增加邮件模板配置
  * @param emailTemplate 请求实体
  * @return boolean  
  * @author 王共亮
  * @date 2020年11月20日 上午9:26:25
  */
  public boolean addEmailTemplate(EmailTemplate emailTemplate);

  /**
  * @Title: updateEmailTemplate 
  * @Description: 修改邮件模板配置
  * @param id 主键
  * @param emailTemplate 请求实体
  * @return boolean  
  * @author 王共亮
  * @date 2020年11月20日 上午9:28:53
  */
  public boolean updateEmailTemplate(int id, EmailTemplate emailTemplate);

  /**
  * @Title: deleteEmailTemplate 
  * @Description: 根据主键删除邮件模板配置
  * @param id 主键
  * @return boolean  
  * @author 王共亮
  * @date 2020年11月20日 上午9:30:05
  */
  public boolean deleteEmailTemplate(int id);

  /**
  * @Title: queryEmailById 
  * @Description: 根据主键查询详情
  * @param id 主键
  * @return EmailTemplate 邮件模板实体  
  * @author 王共亮
  * @date 2020年11月20日 上午9:31:31
  */
  public EmailTemplate queryEmailById(int id);

  /**
  * @Title: queryEmailTemplateList 
  * @Description: 分页查询邮件配置模板
  * @param templateId 模板id
  * @param templateName 模板名称
  * @param pageSize 每页显示的数量
  * @param pageNum 当前页码
  * @return Page<EmailTemplate>  
  * @author 王共亮
  * @date 2020年11月20日 上午9:36:26
  */
  public Page<EmailTemplate> queryEmailTemplateList(String templateId, String templateName, int pageNum, int pageSize);
}
