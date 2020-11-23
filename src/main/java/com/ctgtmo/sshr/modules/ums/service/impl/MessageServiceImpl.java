package com.ctgtmo.sshr.modules.ums.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

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
import com.ctgtmo.sshr.modules.ums.dto.EmailParam;
import com.ctgtmo.sshr.modules.ums.mapper.EmailTemplateMapper;
import com.ctgtmo.sshr.modules.ums.mapper.WxMessageTemplateMapper;
import com.ctgtmo.sshr.modules.ums.model.email.EmailLog;
import com.ctgtmo.sshr.modules.ums.model.email.EmailTemplate;
import com.ctgtmo.sshr.modules.ums.model.wx.Data;
import com.ctgtmo.sshr.modules.ums.model.wx.Miniprogram;
import com.ctgtmo.sshr.modules.ums.model.wx.TemplateMessage;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageLog;
import com.ctgtmo.sshr.modules.ums.model.wx.WxMessageTemplate;
import com.ctgtmo.sshr.modules.ums.service.EmailLogService;
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
@SuppressWarnings("all")
@Service("messageService")
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

  //邮件mapper
  @Autowired
  private EmailTemplateMapper emailMapper;

  //邮件service
  @Autowired
  private EmailLogService logService;

  //邮件默认配置信息
  public static Properties defaultConfig(Boolean debug) {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.ssl.enable", "true");
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.debug", null != debug ? debug.toString() : "false");
    props.put("mail.smtp.timeout", "10000");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    return props;
  }

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

  /**
   * @Title: sendTemplateEmail 
   * @Description: 使用模板发送邮件
   * @param emailParam 邮件请求类
   * @throws ApiException CommonResult<?>  
   * @author 王共亮
   * @date 2020年11月20日 下午2:51:26
   */
  @Override
  public CommonResult<?> sendTemplateEmail(EmailParam emailParam) throws ApiException {
    String templateId = emailParam.getTemplateId();
    if (StringUtils.isNotBlank(templateId)) {
      //先根据模板id获取配置
      EmailTemplate email = emailMapper.queryEmailTemplate(templateId);
      //正常处理业务
      if (null != email) {
        //日志实体
        List<EmailLog> logList = new ArrayList<>();
        //用户名
        String userName = email.getUserName();
        //用户密码
        String passWord = email.getPassWord();
        //邮件标题
        String subject = email.getSubject();
        //邮件服务器host
        String host = email.getHost();
        //邮件服务器port
        String port = email.getPort();
        //邮件发送人
        String fromEmail = email.getFromMail();
        //模板内容
        String templateContent = email.getContent();
        //需要发送的邮件内容
        String emailContent = "";
        //邮箱服务器配置
        Properties properties = defaultConfig(false);
        //添加端口号等信息
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        int status = 0;
        String returnMsg = "";
        try {
          // 根据属性新建一个邮件会话
          Session session = Session.getInstance(properties);
          // 由邮件会话新建一个消息对象
          MimeMessage message = new MimeMessage(session);
          // 1：设置发件人的地址
          message.setFrom(new InternetAddress(userName));
          // 2：设置标题
          message.setSubject(subject);
          // 3：处理邮件内容,这里份两种情况，一种是直接使用传递过来的不需要替换文本中的关键词，另一种是模板设置好的需要替换关键字
          if (StringUtils.isNotBlank(emailParam.getContent())) {
            //直接使用
            emailContent = emailParam.getContent();
          } else {
            //替换html中的关键字
            emailContent = buildContent(templateContent, emailParam.getTemplateValue());
          }
          // 3：处理收件人,考虑多个的情况
          String[] toMailList = null;
          String toMails = emailParam.getToMails();
          if (StringUtils.isNotBlank(toMails)) {
            toMailList = toMails.split(",");
          }
          InternetAddress[] sendTo = new InternetAddress[toMailList.length];
          for (int i = 0; i < toMailList.length; i++) {
            sendTo[i] = new InternetAddress(toMailList[i]);
            EmailLog emailLog = new EmailLog();
            emailLog.setReceiveMail(toMailList[i]);
            emailLog.setTemplateId(templateId);
            emailLog.setSubject(subject);
            emailLog.setContent(emailContent);
            emailLog.setTemplateName(email.getTemplateName());

          }
          // 4：设置收件人,并设置其接收类型为TO
          message.setRecipients(Message.RecipientType.TO, sendTo);
          //处理接收人,考虑多个的情况
          String[] ccMailList = null;
          String ccMails = emailParam.getCcMails();
          if (StringUtils.isNotBlank(ccMails)) {
            ccMailList = ccMails.split(",");
            InternetAddress[] toCC = new InternetAddress[ccMailList.length];
            for (int i = 0; i < ccMailList.length; i++) {
              toCC[i] = new InternetAddress(ccMailList[i]);
            }
            // 设置抄手人,并设置其接收类型为CC
            message.setRecipients(Message.RecipientType.CC, toCC);
          }
          // 5：处理邮件内容,这里份两种情况，一种是直接使用传递过来的不需要替换文本中的关键词，另一种是模板设置好的需要替换关键字
          if (StringUtils.isNotBlank(emailParam.getContent())) {
            //直接使用
            emailContent = emailParam.getContent();
          } else {
            //替换html中的关键字
            emailContent = buildContent(templateContent, emailParam.getTemplateValue());
          }
          // 6：设置邮件内容，混合模式
          MimeMultipart msgMultipart = new MimeMultipart("mixed");
          message.setContent(msgMultipart);
          // 7：设置消息正文
          MimeBodyPart content = new MimeBodyPart();
          msgMultipart.addBodyPart(content);
          // 8：设置正文格式
          MimeMultipart bodyMultipart = new MimeMultipart("related");
          content.setContent(bodyMultipart);
          // 9：设置正文内容
          MimeBodyPart htmlPart = new MimeBodyPart();
          bodyMultipart.addBodyPart(htmlPart);
          htmlPart.setContent(emailContent, "text/html;charset=UTF-8");
          //10：处理邮件附件
          List<HashMap<String, Object>> fileList = emailParam.getFileList();
          if (!Collections.isEmpty(fileList)) {
            for (int i = 0; i < fileList.size(); i++) {
              //文件名称
              String fileName = fileList.get(i).get("fileName").toString();
              //文件流
              byte[] bytes = (byte[]) fileList.get(i).get("data");
              //装载附件并将添加到MIME消息体中
              MimeBodyPart filePart = new MimeBodyPart();
              //添加附件流
              ByteArrayDataSource dataSource = new ByteArrayDataSource(bytes, "text/data");
              DataHandler dataHandler = new DataHandler(dataSource);
              // 文件处理
              filePart.setDataHandler(dataHandler);
              // 附件名称设置GBK编码防止中文乱码
              filePart.setFileName(MimeUtility.encodeText(fileName, "GBK", "B"));
              // 放入正文（有先后顺序，所以在正文后面）
              msgMultipart.addBodyPart(filePart);
            }
          }
          // 设置发送时间
          message.setSentDate(new Date());
          // 存储邮件信息
          message.saveChanges();
          // 发送邮件
          Transport transport = session.getTransport();
          //验证用户名和密码
          transport.connect(userName, passWord);
          transport.sendMessage(message, message.getAllRecipients());
          transport.close();
          //存入邮件发送日志-批量
          logService.saveBatch(logList);
        } catch (Exception e) {
          status = 1;
          returnMsg = e.getMessage();
          return CommonResult.failed();
        }
        //批量增加日志
        logService.saveBatch(logList);
        return CommonResult.success(null);
      } else {
        return CommonResult.failed("无效的templateId");
      }
    } else {
      return CommonResult.failed("templateId不能为空");
    }
  }

  /**
   * 获取html模板，填充html模板中的动态参数
   * @param htmlText 模板
   * @param templateValue 模板对应的值
   * @return
   */
  private String buildContent(String htmlText, Map<String, Object> templateValue) {
    if (!templateValue.isEmpty() && templateValue != null) {
      for (Map.Entry<String, Object> entry : templateValue.entrySet()) {
        htmlText = htmlText.replace("{" + entry.getKey() + "}", entry.getValue().toString());
      }
    }
    return htmlText;
  }
}
