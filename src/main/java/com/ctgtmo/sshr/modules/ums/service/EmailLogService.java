package com.ctgtmo.sshr.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgtmo.sshr.modules.ums.model.email.EmailLog;

/**
 * @Title: EmailLogService.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.service   
 * @Description: 邮件日志service
 * @author: 王共亮     
 * @date: 2020年11月23日 上午10:45:30
 */
public interface EmailLogService extends IService<EmailLog> {

  /**
   * 分页查询日志
   */
  Page<EmailLog> list(String templateName, String receiveMail, String subject, int status, int pageNum, int pageSize);
}
