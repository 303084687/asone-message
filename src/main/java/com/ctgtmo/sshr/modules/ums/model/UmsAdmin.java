package com.ctgtmo.sshr.modules.ums.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdmin implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String username;

  private String password;

  //头像
  private String icon;

  //邮箱
  private String email;

  //昵称
  private String nickName;

  //备注信息
  private String note;

  //创建时间
  private Date createTime;

  //最后登录时间
  private Date loginTime;

  //帐号启用状态：0->禁用；1->启用
  private Integer status;

}
