package com.ctgtmo.sshr.modules.ums.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 后台资源表
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_resource")
public class UmsResource implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  //创建时间
  private Date createTime;

  //资源名称
  private String name;

  //资源URL
  private String url;

  //描述
  private String description;

  //资源分类ID
  private Long categoryId;

}
