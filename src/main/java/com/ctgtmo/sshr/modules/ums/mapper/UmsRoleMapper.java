package com.ctgtmo.sshr.modules.ums.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgtmo.sshr.modules.ums.model.UmsRole;

/**
 * 后台用户角色表 Mapper 接口
 * @author macro
 * @since 2020-08-21
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

  /**
   * 获取用户所有角色
   */
  List<UmsRole> getRoleList(@Param("adminId") Long adminId);

}
