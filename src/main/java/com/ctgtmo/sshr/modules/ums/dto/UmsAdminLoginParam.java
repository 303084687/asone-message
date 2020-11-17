package com.ctgtmo.sshr.modules.ums.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {
  @NotEmpty
  private String username;

  @NotEmpty
  private String password;
}
