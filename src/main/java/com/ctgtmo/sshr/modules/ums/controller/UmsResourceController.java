package com.ctgtmo.sshr.modules.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgtmo.sshr.common.api.CommonPage;
import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.modules.ums.model.UmsResource;
import com.ctgtmo.sshr.modules.ums.service.UmsResourceService;
import com.ctgtmo.sshr.security.component.DynamicSecurityMetadataSource;

/**
 * 后台资源管理Controller
 * Created by macro on 2020/2/4.
 */
@Controller
@RequestMapping("/resource")
@SuppressWarnings("all")
public class UmsResourceController {

  @Autowired
  private UmsResourceService resourceService;

  @Autowired
  private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult create(@RequestBody UmsResource umsResource) {
    boolean success = resourceService.create(umsResource);
    dynamicSecurityMetadataSource.clearDataSource();
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  //修改后台资源
  @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult update(@PathVariable Long id, @RequestBody UmsResource umsResource) {
    boolean success = resourceService.update(id, umsResource);
    dynamicSecurityMetadataSource.clearDataSource();
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<UmsResource> getItem(@PathVariable Long id) {
    UmsResource umsResource = resourceService.getById(id);
    return CommonResult.success(umsResource);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult delete(@PathVariable Long id) {
    boolean success = resourceService.delete(id);
    dynamicSecurityMetadataSource.clearDataSource();
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String nameKeyword,
  @RequestParam(required = false) String urlKeyword, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
    Page<UmsResource> resourceList = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
    return CommonResult.success(CommonPage.restPage(resourceList));
  }

  @RequestMapping(value = "/listAll", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<UmsResource>> listAll() {
    List<UmsResource> resourceList = resourceService.list();
    return CommonResult.success(resourceList);
  }
}
