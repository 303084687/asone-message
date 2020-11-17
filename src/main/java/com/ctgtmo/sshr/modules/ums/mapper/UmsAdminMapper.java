package com.ctgtmo.sshr.modules.ums.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgtmo.sshr.modules.ums.model.UmsAdmin;

/**
 * 后台用户表 Mapper 接口
 * @author macro
 * @since 2020-08-21
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

  /**
   * 获取资源相关用户ID列表
   */
  List<Long> getAdminIdList(@Param("resourceId") Long resourceId);

}
