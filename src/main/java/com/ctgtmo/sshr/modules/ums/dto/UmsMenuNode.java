package com.ctgtmo.sshr.modules.ums.dto;

import java.util.List;

import com.ctgtmo.sshr.modules.ums.model.UmsMenu;

import lombok.Getter;
import lombok.Setter;

/**
 * 后台菜单节点封装
 * Created by macro on 2020/2/4.
 */
@Getter
@Setter
@SuppressWarnings("all")
public class UmsMenuNode extends UmsMenu {
  //子级菜单
  private List<UmsMenuNode> children;
}
