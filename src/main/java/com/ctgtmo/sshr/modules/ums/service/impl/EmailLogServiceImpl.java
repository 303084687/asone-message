package com.ctgtmo.sshr.modules.ums.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgtmo.sshr.modules.ums.mapper.EmailLogMapper;
import com.ctgtmo.sshr.modules.ums.model.email.EmailLog;
import com.ctgtmo.sshr.modules.ums.service.EmailLogService;

/**
 * @Title: WxMessageLogServiceImpl.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.service.impl   
 * @Description: 微信消息日志Service实现类
 * @author: 王共亮     
 * @date: 2020年10月29日 下午2:07:51
 */
@Service
public class EmailLogServiceImpl extends ServiceImpl<EmailLogMapper, EmailLog> implements EmailLogService {

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
  @Override
  @Transactional(readOnly = true)
  public Page<EmailLog> list(String templateName, String receiveMail, String subject, int status, int pageNum, int pageSize) {
    //分页参数
    Page<EmailLog> page = new Page<>(pageNum, pageSize);
    QueryWrapper<EmailLog> wrapper = new QueryWrapper<>();
    LambdaQueryWrapper<EmailLog> lambda = wrapper.lambda();
    //拼接查询参数
    if (StringUtils.isNotBlank(templateName)) {
      lambda.like(EmailLog::getTemplateName, templateName);
    }
    if (StringUtils.isNotBlank(receiveMail)) {
      lambda.like(EmailLog::getReceiveMail, receiveMail);
    }
    if (StringUtils.isNotBlank(subject)) {
      lambda.like(EmailLog::getSubject, subject);
    }
    //消息状态
    if (status == 0 || status == 1) {
      lambda.eq(EmailLog::getStatus, status);
    }
    //按照创建时间排序
    lambda.orderByDesc(EmailLog::getId);
    return page(page, wrapper);
  }
}
