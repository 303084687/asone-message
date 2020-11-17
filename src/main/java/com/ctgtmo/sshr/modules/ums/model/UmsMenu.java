package com.ctgtmo.sshr.modules.ums.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p> 后台菜单表 </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_menu")
public class UmsMenu implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  //父级ID
  private Long parentId;

  //创建时间
  private Date createTime;

  //菜单名称
  private String title;

  //菜单级数
  private Integer level;

  //菜单排序
  private Integer sort;

  //前端名称
  private String name;

  //前端图标
  private String icon;

  //前端隐藏
  private Integer hidden;

}
