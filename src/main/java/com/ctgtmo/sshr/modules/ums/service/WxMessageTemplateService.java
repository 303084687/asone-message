package com.ctgtmo.sshr.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageTemplate;

/**
 * @Title: WxMessageTemplateService.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.service   
 * @Description: 微信模板Service
 * @author: 王共亮     
 * @date: 2020年10月29日 下午2:01:39
 */
public interface WxMessageTemplateService extends IService<WxMessageTemplate> {
  /**
   * 添加微信推送消息模板资源
   */
  CommonResult<?> create(WxMessageTemplate wxMessageTemplate);

  /**
   * 修改微信推送消息模板资源
   */
  boolean update(int id, WxMessageTemplate wxMessageTemplate);

  /**
   * 删除微信推送消息模板资源
   */
  boolean delete(String templateId);

  /**
   * 分页查询资源
   */
  Page<WxMessageTemplate> list(String templateId, String templateName, Integer pageSize, Integer pageNum);
}
