package com.ctgtmo.sshr.modules.ums.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户角色表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_role")
public class UmsRole implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  //名称
  private String name;

  //描述
  private String description;

  //后台用户数量
  private Integer adminCount;

  //创建时间
  private Date createTime;

  //启用状态：0->禁用；1->启用
  private Integer status;

  private Integer sort;

}
