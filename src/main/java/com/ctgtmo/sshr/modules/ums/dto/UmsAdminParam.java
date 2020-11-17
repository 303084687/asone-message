package com.ctgtmo.sshr.modules.ums.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Getter
@Setter
public class UmsAdminParam {
  //用户名", required = true)
  @NotEmpty
  private String username;

  //密码", required = true)
  @NotEmpty
  private String password;

  //用户头像
  private String icon;

  //邮箱
  @Email
  private String email;

  //用户昵称
  private String nickName;

  //备注
  private String note;
}
