package com.ctgtmo.sshr.modules.ums.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgtmo.sshr.modules.ums.model.UmsMenu;

/**
 * 后台菜单表 Mapper 接口
 * @author macro
 * @since 2020-08-21
 */
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

  /**
   * 根据后台用户ID获取菜单
   */
  List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

  /**
   * 根据角色ID获取菜单
   */
  List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

}
