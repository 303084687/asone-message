package com.ctgtmo.sshr.modules.ums.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgtmo.sshr.modules.ums.model.email.EmailTemplate;

/**
 * @Title: EmailTemplateMapper.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.mapper   
 * @Description: 邮件配置接口
 * @author: 王共亮     
 * @date: 2020年11月19日 下午5:23:53
 */
public interface EmailTemplateMapper extends BaseMapper<EmailTemplate> {
  /**
  * @Title: queryEmailTemplate 
  * @Description: 根据模板主键查询详情
  * @param templateId 邮件模板主键
  * @return EmailTemplate  
  * @author 王共亮
  * @date 2020年11月20日 上午9:47:12
  */
  EmailTemplate queryEmailTemplate(@Param("templateId") String templateId);
}
