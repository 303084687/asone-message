package com.ctgtmo.sshr.modules.ums.service.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgtmo.sshr.modules.ums.mapper.EmailTemplateMapper;
import com.ctgtmo.sshr.modules.ums.model.email.EmailTemplate;
import com.ctgtmo.sshr.modules.ums.service.EmailTemplateService;

/**
 * @Title: EmailTemplateServiceImpl.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.service.impl   
 * @Description: 邮件模板配置service实现类
 * @author: 王共亮     
 * @date: 2020年11月19日 下午5:37:19
 */
@Service
public class EmailTemplateServiceImpl extends ServiceImpl<EmailTemplateMapper, EmailTemplate> implements EmailTemplateService {
  /**
  * @Title: addEmailTemplate 
  * @Description: 增加邮件模板配置
  * @param emailTemplate 请求实体
  * @return boolean  
  * @author 王共亮
  * @date 2020年11月20日 上午9:26:25
  */
  @Override
  @Transactional
  public boolean addEmailTemplate(EmailTemplate emailTemplate) {
    //设置邮件templateId
    emailTemplate.setTemplateId(UUID.randomUUID().toString().replaceAll("-", ""));
    return save(emailTemplate);
  }

  /**
  * @Title: updateEmailTemplate 
  * @Description: 修改邮件模板配置
  * @param id 主键
  * @param emailTemplate 请求实体
  * @return boolean  
  * @author 王共亮
  * @date 2020年11月20日 上午9:28:53
  */
  @Override
  @Transactional
  public boolean updateEmailTemplate(int id, EmailTemplate emailTemplate) {
    emailTemplate.setId(id);
    return updateById(emailTemplate);
  }

  /**
  * @Title: deleteEmailTemplate 
  * @Description: 根据主键删除邮件模板配置
  * @param id 主键
  * @return boolean  
  * @author 王共亮
  * @date 2020年11月20日 上午9:30:05
  */
  @Override
  @Transactional
  public boolean deleteEmailTemplate(int id) {
    return removeById(id);
  }

  /**
  * @Title: queryEmailById 
  * @Description: 根据主键查询详情
  * @param id 主键
  * @return EmailTemplate 邮件模板实体  
  * @author 王共亮
  * @date 2020年11月20日 上午9:31:31
  */
  @Override
  @Transactional(readOnly = true)
  public EmailTemplate queryEmailById(int id) {
    return getById(id);
  }

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
  @Override
  @Transactional(readOnly = true)
  public Page<EmailTemplate> queryEmailTemplateList(String templateId, String templateName, int pageNum, int pageSize) {
    Page<EmailTemplate> page = new Page<>(pageNum, pageSize);
    QueryWrapper<EmailTemplate> wrapper = new QueryWrapper<>();
    LambdaQueryWrapper<EmailTemplate> lambda = wrapper.lambda();
    //拼接查询参数
    if (StringUtils.isNotBlank(templateId)) {
      lambda.eq(EmailTemplate::getTemplateId, templateId);
    }
    if (StringUtils.isNotBlank(templateName)) {
      lambda.like(EmailTemplate::getTemplateName, templateName);
    }
    //按照创建时间排序
    lambda.orderByDesc(EmailTemplate::getCreateTime);
    return page(page, wrapper);
  }
}
