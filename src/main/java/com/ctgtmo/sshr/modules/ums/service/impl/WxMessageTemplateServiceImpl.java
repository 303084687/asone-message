package com.ctgtmo.sshr.modules.ums.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.modules.ums.mapper.WxMessageTemplateMapper;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageTemplate;
import com.ctgtmo.sshr.modules.ums.service.WxMessageTemplateService;

import io.jsonwebtoken.lang.Collections;

/**
 * @Title: WxMessageTemplateServiceImpl.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.service.impl   
 * @Description: 微信消息模板Service实现类
 * @author: 王共亮     
 * @date: 2020年10月29日 下午2:25:15
 */
@Service
public class WxMessageTemplateServiceImpl extends ServiceImpl<WxMessageTemplateMapper, WxMessageTemplate> implements WxMessageTemplateService {
  @Autowired
  private WxMessageTemplateMapper mapper;

  /** 
  * @Title: create 
  * @Description: 创建微信模板
  * @param wxMessageTemplate
  * @author 王共亮
  * @date 2020年10月29日 下午2:25:48
  */
  @Override
  @Transactional
  public CommonResult<?> create(WxMessageTemplate wxMessageTemplate) {
    //先查询是否存在
    List<WxMessageTemplate> templateList = mapper.queryMessageTemplate(wxMessageTemplate.getTemplateId());
    if (Collections.isEmpty(templateList)) {
      wxMessageTemplate.setCreateTime(new Date());
      boolean success = save(wxMessageTemplate);
      if (success) {
        return CommonResult.success(null);
      } else {
        return CommonResult.failed();
      }
    } else {
      return CommonResult.failed("templateId已存在");
    }
  }

  /** 
  * @Title: update 
  * @Description: 修改微信模板
  * @param templateId 模板主键
  * @param wxMessageTemplate
  * @return 
  * @author 王共亮
  * @date 2020年10月29日 下午2:25:48
  */
  @Override
  @Transactional
  public boolean update(int id, WxMessageTemplate wxMessageTemplate) {
    boolean flag = true;
    //先查询是否存在
    List<WxMessageTemplate> templateList = mapper.queryMessageTemplate(wxMessageTemplate.getTemplateId());
    //不存在直接修改
    if (Collections.isEmpty(templateList)) {
      wxMessageTemplate.setId(id);
      flag = mapper.updateTemplate(wxMessageTemplate);
    } else {
      for (int i = 0; i < templateList.size(); i++) {
        //存在的情况下判断templateId是否相同,相同情况下允许修改
        if (id == templateList.get(i).getId()) {
          flag = mapper.updateTemplate(wxMessageTemplate);
        }
      }
    }
    return flag;
  }

  /** 
  * @Title: delete 
  * @Description: 删除微信模板
  * @param templateId 模板主键
  * @author 王共亮
  * @date 2020年10月29日 下午2:25:48
  */
  @Override
  @Transactional
  public boolean delete(String templateId) {
    return mapper.deleteTemplate(templateId);
  }

  /** 
  * @Title: list 
  * @Description: 分页查询数据
  * @param pageSize 当前页码
  * @param pageNum 每页显示的数量
  * @author 王共亮
  * @date 2020年10月29日 下午2:25:48
  */
  @Override
  @Transactional(readOnly = true)
  public Page<WxMessageTemplate> list(String templateId, String templateName, Integer pageSize, Integer pageNum) {
    Page<WxMessageTemplate> page = new Page<>(pageNum, pageSize);
    QueryWrapper<WxMessageTemplate> wrapper = new QueryWrapper<>();
    LambdaQueryWrapper<WxMessageTemplate> lambda = wrapper.lambda();
    //拼接查询参数
    if (StringUtils.isNotBlank(templateId)) {
      lambda.eq(WxMessageTemplate::getTemplateId, templateId);
    }
    if (StringUtils.isNotBlank(templateName)) {
      lambda.like(WxMessageTemplate::getTemplateName, templateName);
    }
    //按照创建时间排序
    lambda.orderByDesc(WxMessageTemplate::getCreateTime);
    return page(page, wrapper);
  }
}
