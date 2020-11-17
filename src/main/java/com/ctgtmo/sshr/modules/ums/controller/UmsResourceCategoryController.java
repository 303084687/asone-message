package com.ctgtmo.sshr.modules.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.modules.ums.model.UmsResourceCategory;
import com.ctgtmo.sshr.modules.ums.service.UmsResourceCategoryService;

/**
 * 后台资源分类管理Controller
 * Created by macro on 2020/2/5.
 */
@Controller
@RequestMapping("/resourceCategory")
@SuppressWarnings("all")
public class UmsResourceCategoryController {
  @Autowired
  private UmsResourceCategoryService resourceCategoryService;

  @RequestMapping(value = "/listAll", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<UmsResourceCategory>> listAll() {
    List<UmsResourceCategory> resourceList = resourceCategoryService.listAll();
    return CommonResult.success(resourceList);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult create(@RequestBody UmsResourceCategory umsResourceCategory) {
    boolean success = resourceCategoryService.create(umsResourceCategory);
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult update(@PathVariable Long id, @RequestBody UmsResourceCategory umsResourceCategory) {
    umsResourceCategory.setId(id);
    boolean success = resourceCategoryService.updateById(umsResourceCategory);
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult delete(@PathVariable Long id) {
    boolean success = resourceCategoryService.removeById(id);
    if (success) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }
}
