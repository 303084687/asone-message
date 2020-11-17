package com.ctgtmo.sshr.modules.ums.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgtmo.sshr.modules.ums.model.UmsAdminLoginLog;

/**
 * 后台用户登录日志表 Mapper 接口
 * @author macro
 * @since 2020-08-21
 */
public interface UmsAdminLoginLogMapper extends BaseMapper<UmsAdminLoginLog> {
  void updateLoginTime(@Param("id") Long id);
}
