package com.ctgtmo.sshr.modules.ums.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageTemplate;

/**
 * @Title: WxMessageTemplateMapper.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.modules.ums.mapper   
 * @Description: 微信模板mapper
 * @author: 王共亮     
 * @date: 2020年10月29日 上午11:40:36
 */
public interface WxMessageTemplateMapper extends BaseMapper<WxMessageTemplate> {
  /** 
  * @Title: deleteTemplate 
  * @Description: 删除微信消息模板
  * @param templateId 模板主键
  * @author 王共亮
  * @date 2020年10月29日 下午5:54:15
  */
  Boolean deleteTemplate(@Param("templateId") String templateId);

  /**
  * @Title: queryMessageTemplate 
  * @Description: 根据模板主键查询详情
  * @param templateId 模板主键
  * @return WxMessageTemplate  
  * @author 王共亮
  * @date 2020年10月30日 下午2:42:23
   */
  List<WxMessageTemplate> queryMessageTemplate(@Param("templateId") String templateId);

  /** 
  * @Title: updateTemplate 
  * @Description: 修改微信消息模板
  * @param template 模板实体
  * @author 王共亮
  * @date 2020年10月30日 下午3:48:43
  */
  Boolean updateTemplate(WxMessageTemplate template);
}
