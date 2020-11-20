package com.ctgtmo.sshr.modules.ums.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.common.exception.ApiException;
import com.ctgtmo.sshr.config.WeChetAccessToken;
import com.ctgtmo.sshr.config.WxChatConstant;
import com.ctgtmo.sshr.modules.ums.mapper.WxMessageTemplateMapper;
import com.ctgtmo.sshr.modules.ums.model.wx.Data;
import com.ctgtmo.sshr.modules.ums.model.wx.Miniprogram;
import com.ctgtmo.sshr.modules.ums.model.wx.TemplateMessage;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageLog;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageTemplate;
import com.ctgtmo.sshr.modules.ums.service.MessageService;
import com.ctgtmo.sshr.utils.HttpRequest;

import io.jsonwebtoken.lang.Collections;

/**  
 * @Title: MessageServiceImpl.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.service.impl   
 * @Description: 消息接口推送实现类
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:58:41   
 */
@Service("wxChatService")
public class MessageServiceImpl implements MessageService {
  //获取token
  @Autowired
  protected WeChetAccessToken weChetAccessToken;

  //消息模板
  @Autowired
  private WxMessageTemplateMapper templateMapper;

  //消息日志
  @Autowired
  protected BaseMapper<WxMessageLog> baseMapper;

  /**
   * @Title: sendTemplateMessage 
   * @Description: 发送微信模板消息
   * @param templateId 模板id
   * @param touser 接收者微信openId
   * @param data 模板数据
   * @throws ApiException JSONObject  
   * @author 王共亮
   * @date 2020年11月2日 下午2:48:53
   */
  @Override
  public CommonResult<?> sendTemplateMessage(String templateId, String touser, Data data) throws ApiException {
    //先根据模板id获取配置
    List<WxMessageTemplate> list = templateMapper.queryMessageTemplate(templateId);
    if (!Collections.isEmpty(list)) {
      //微信公众号appId
      String appId = list.get(0).getAppId();
      //微信公众号secret
      String secret = list.get(0).getSecret();
      //微信模板template_id
      String wxTemplateId = list.get(0).getTemplateId();
      //获取token
      String accessToken = weChetAccessToken.getToken(appId, secret);
      //替换请求地址中的accessToken的值
      String postUrl = WxChatConstant.Url.SEND_URL.replace("ACCESS_TOKEN", accessToken);
      //创建消息发送实体对象
      TemplateMessage templateMessage = new TemplateMessage();
      templateMessage.setTouser(touser);
      templateMessage.setTemplate_id(wxTemplateId);
      //微信模板跳转链接
      String wxUrl = list.get(0).getUrl();
      if (StringUtils.isNotBlank(wxUrl)) {
        templateMessage.setUrl(wxUrl);
      }
      //拼接微信小程序参数
      String miniAppId = list.get(0).getMiniAppId();
      String miniPagePath = list.get(0).getMiniPagePath();
      if (StringUtils.isNotBlank(miniAppId)) {
        Miniprogram miniprogram = new Miniprogram();
        miniprogram.setAppid(miniAppId);
        miniprogram.setPagepath(miniPagePath);
        templateMessage.setMiniprogram(miniprogram);
      }
      //拼接模板数据
      templateMessage.setData(data);
      //json格式化参数
      String params = JSON.toJSONString(templateMessage);
      //发送请求并获取结果
      String result = HttpRequest.sendPost(postUrl, params);
      JSONObject jsonResult = JSON.parseObject(result);
      if (null != jsonResult) {
        //获取返回code和message
        int code = jsonResult.getInteger("errcode");
        String errmsg = jsonResult.getString("errmsg");
        //code等于0为成功其它为失败,存入消息日志
        WxMessageLog messageLog = new WxMessageLog();
        messageLog.setTouser(touser);
        messageLog.setContent(params);
        messageLog.setTemplateId(wxTemplateId);
        messageLog.setTemplateName(list.get(0).getTemplateName());
        messageLog.setMessage(errmsg);
        messageLog.setStatus(code == 0 ? 0 : 1);
        messageLog.setCreateTime(new Date());
        //存入消息发送日志
        baseMapper.insert(messageLog);
        //返回前端结果
        if (code == 0) {
          return CommonResult.success(null);
        } else {
          return CommonResult.failed(errmsg);
        }
      }
    } else {
      return CommonResult.failed("无效的templateId");
    }
    return null;
  }

  /**
   * @Title: sendTemplateMessage 
   * @Description: 发送微信模板消息--以map方式传值
   * @param map 传递参数
   * @throws ApiException CommonResult<?>  
   * @author 王共亮
   * @date 2020年11月3日 上午10:01:55
    */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public CommonResult<?> sendTemplateMessage(Map<String, Object> map) throws ApiException {
    //微信消息模板id
    String templateId = map.get("tempalteId").toString();
    //消息接收者openId
    String touser = map.get("touser").toString();
    //先根据模板id查询配置参数
    if (StringUtils.isNotBlank(templateId)) {
      List<WxMessageTemplate> list = templateMapper.queryMessageTemplate(templateId);
      if (!Collections.isEmpty(list)) {
        //微信公众号appId
        String appId = list.get(0).getAppId();
        //微信公众号secret
        String secret = list.get(0).getSecret();
        //微信模板template_id
        String wxTemplateId = list.get(0).getTemplateId();
        //获取token
        String accessToken = weChetAccessToken.getToken(appId, secret);
        //替换请求地址中的accessToken的值
        String postUrl = WxChatConstant.Url.SEND_URL.replace("ACCESS_TOKEN", accessToken);
        //创建消息发送实体对象
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTouser(touser);
        templateMessage.setTemplate_id(wxTemplateId);
        //微信模板跳转链接
        String wxUrl = list.get(0).getUrl();
        if (StringUtils.isNotBlank(wxUrl)) {
          templateMessage.setUrl(wxUrl);
        }
        //拼接微信小程序参数
        String miniAppId = list.get(0).getMiniAppId();
        String miniPagePath = list.get(0).getMiniPagePath();
        if (StringUtils.isNotBlank(miniAppId)) {
          Miniprogram miniprogram = new Miniprogram();
          miniprogram.setAppid(miniAppId);
          miniprogram.setPagepath(miniPagePath);
          templateMessage.setMiniprogram(miniprogram);
        }
        //获取参数数据并拼接模板数据
        Object data = map.get("data");
        templateMessage.setData(data);
        //json格式化参数
        String params = JSON.toJSONString(templateMessage);
        //发送请求并获取结果
        String result = HttpRequest.sendPost(postUrl, params);
        JSONObject jsonResult = JSON.parseObject(result);
        if (null != jsonResult) {
          //获取返回code和message
          int code = jsonResult.getInteger("errcode");
          String errmsg = jsonResult.getString("errmsg");
          //code等于0为成功其它为失败,存入消息日志
          WxMessageLog messageLog = new WxMessageLog();
          messageLog.setTouser(touser);
          messageLog.setContent(params);
          messageLog.setTemplateId(wxTemplateId);
          messageLog.setTemplateName(list.get(0).getTemplateName());
          messageLog.setMessage(errmsg);
          messageLog.setStatus(code == 0 ? 0 : 1);
          messageLog.setCreateTime(new Date());
          //存入消息发送日志
          baseMapper.insert(messageLog);
          //返回前端结果
          if (code == 0) {
            return CommonResult.success(null);
          } else {
            return CommonResult.failed(errmsg);
          }
        }
      } else {
        return CommonResult.failed("无效的templateId");
      }
    } else {
      return CommonResult.failed("templateId不能为空");
    }
    return null;
  }
}
