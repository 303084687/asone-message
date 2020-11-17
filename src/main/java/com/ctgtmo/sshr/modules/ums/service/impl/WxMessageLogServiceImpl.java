package com.ctgtmo.sshr.modules.ums.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgtmo.sshr.modules.ums.mapper.WxMessageLogMapper;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageLog;
import com.ctgtmo.sshr.modules.ums.service.WxMessageLogService;

/**
 * @Title: WxMessageLogServiceImpl.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.service.impl   
 * @Description: 微信消息日志Service实现类
 * @author: 王共亮     
 * @date: 2020年10月29日 下午2:07:51
 */
@Service
public class WxMessageLogServiceImpl extends ServiceImpl<WxMessageLogMapper, WxMessageLog> implements WxMessageLogService {

  /** 
  * @Title: list 
  * @Description: 查询日志列表
  * @param templateId 模板id
  * @param touser 微信openId
  * @param status 状态
  * @param pageSize 当前页码
  * @param pageNum 每页显示的数量
  * @author 王共亮
  * @date 2020年10月29日 下午2:08:59
  */
  @Override
  @Transactional(readOnly = true)
  public Page<WxMessageLog> list(String templateId, String touser, int status, Integer pageSize, Integer pageNum) {
    //分页参数
    Page<WxMessageLog> page = new Page<>(pageNum, pageSize);
    QueryWrapper<WxMessageLog> wrapper = new QueryWrapper<>();
    LambdaQueryWrapper<WxMessageLog> lambda = wrapper.lambda();
    //拼接查询参数
    if (StringUtils.isNotBlank(templateId)) {
      lambda.eq(WxMessageLog::getTemplateId, templateId);
    }
    if (StringUtils.isNotBlank(touser)) {
      lambda.like(WxMessageLog::getTouser, touser);
    }
    //消息状态
    if (status == 0 || status == 1) {
      lambda.eq(WxMessageLog::getStatus, status);
    }
    //按照创建时间排序
    lambda.orderByDesc(WxMessageLog::getId);
    return page(page, wrapper);
  }
}
