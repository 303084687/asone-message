package com.ctgtmo.sshr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.common.exception.ApiException;
import com.ctgtmo.sshr.modules.ums.model.wx.Data;
import com.ctgtmo.sshr.modules.ums.model.wx.TemplateContent;
import com.ctgtmo.sshr.modules.ums.service.MessageService;

/**  
 * @Title: WxMessageTest.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.test   
 * @Description: 
 * @author: 王共亮     
 * @date: 2020年10月23日 上午10:58:41   
 */
@SpringBootTest
@SuppressWarnings("all")
@RunWith(SpringRunner.class)
public class WxMessageTest {
  //服务注册
  @Autowired
  private MessageService wxChatService;

  /** 
  * @Title: pushTemplateMessage 
  * @Description:微信公众号测试账号地址http://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
  * @throws ErrorException void  
  * @author 王共亮
  * @date 2020年10月23日 上午11:36:56
  */
  //@Test
  public void pushTemplateMessage() throws ApiException {
    /********************************模板中对应的key*********************************************/
    //设置模板标题
    TemplateContent first = new TemplateContent("您提交的审批，有最新结果！", "#D2691E");
    //设置模板内容
    TemplateContent keyword1 = new TemplateContent("通用表单测试", "#FF0000");
    //设置模板位置
    TemplateContent keyword2 = new TemplateContent("TYBD-170306-00299", "#0000FF");
    //设置设备
    TemplateContent keyword3 = new TemplateContent("审批通过", "#00FF7F");
    //设置时间
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    TemplateContent keyword4 = new TemplateContent(format.format(LocalDateTime.now()), "#808080");
    //设置跳转内容
    TemplateContent remark = new TemplateContent("感谢你的使用", "#FFA500");
    //创建模板信息数据对象
    Data data = new Data();
    data.setFirst(first);
    data.setKeyword1(keyword1);
    data.setKeyword2(keyword2);
    data.setKeyword3(keyword3);
    data.setKeyword4(keyword4);
    data.setRemark(remark);
    String touser = "oZAEusxcZPmw-stEL8_Ga_Qc6rVQ";
    String templateId = "cPN2wZ7w0_isFgR6_gfg2wUKvYhCbFTBCy_FZHZvgo4";
    // 返回和封装结果集
    CommonResult jsonObject = wxChatService.sendTemplateMessage(templateId, touser, data);
    Assert.assertEquals("200", String.valueOf(jsonObject.getCode()));
  }

  /** 
  * @Title: sendTemplateMessage 
  * @Description: 使用map传参的消息推送。微信公众号测试账号地址http://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
  * @throws ErrorException void  
  * @author 王共亮
  * @date 2020年10月23日 上午11:36:37
  */
  @Test
  public void sendTemplateMessage() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    //接收人微信openId
    map.put("touser", "oZAEusxcZPmw-stEL8_Ga_Qc6rVQ");
    //消息模板Id
    map.put("tempalteId", "cPN2wZ7w0_isFgR6_gfg2wUKvYhCbFTBCy_FZHZvgo4");
    /********************************模板中对应的key*********************************************/
    //设置模板标题
    TemplateContent first = new TemplateContent("预警测试", "#D2691E");
    //设置模板内容
    TemplateContent keyword1 = new TemplateContent("测试", "#FF0000");
    //设置模板位置
    TemplateContent keyword2 = new TemplateContent("测试推送消息", "#0000FF");
    //设置设备
    TemplateContent keyword3 = new TemplateContent("传感器设备", "#00FF7F");
    //设置时间
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    TemplateContent keyword4 = new TemplateContent(format.format(LocalDateTime.now()), "#808080");
    //设置跳转内容
    TemplateContent remark = new TemplateContent("点此处查看详情", "#FFA500");
    //创建模板信息数据对象
    Data data = new Data();
    data.setFirst(first);
    data.setKeyword1(keyword1);
    data.setKeyword2(keyword2);
    data.setKeyword3(keyword3);
    data.setKeyword4(keyword4);
    data.setRemark(remark);
    map.put("data", data);
    // 返回和封装结果集
    CommonResult jsonObject = wxChatService.sendTemplateMessage(map);
    Assert.assertEquals("200", String.valueOf(jsonObject.getCode()));
  }
}
