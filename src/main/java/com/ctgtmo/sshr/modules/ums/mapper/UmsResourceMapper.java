package com.ctgtmo.sshr.modules.ums.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgtmo.sshr.modules.ums.model.UmsResource;

/**
 * 后台资源表 Mapper 接口
 * @author macro
 * @since 2020-08-21
 */
public interface UmsResourceMapper extends BaseMapper<UmsResource> {

  /**
   * 获取用户所有可访问资源
   */
  List<UmsResource> getResourceList(@Param("adminId") Long adminId);

  /**
   * 根据角色ID获取资源
   */
  List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);

}
