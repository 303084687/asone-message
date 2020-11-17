package com.ctgtmo.sshr.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageLog;

/**
 * @Title: WxMessageLogService.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.service   
 * @Description: 微信推送日志Service
 * @author: 王共亮     
 * @date: 2020年10月29日 下午2:05:23
 */
public interface WxMessageLogService extends IService<WxMessageLog> {

  /**
   * 分页查询日志
   */
  Page<WxMessageLog> list(String templateId, String touser, int status, Integer pageSize, Integer pageNum);
}
