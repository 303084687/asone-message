package com.ctgtmo.sshr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctgtmo.sshr.common.api.CommonResult;
import com.ctgtmo.sshr.common.exception.ApiException;
import com.ctgtmo.sshr.modules.ums.dto.EmailParam;
import com.ctgtmo.sshr.modules.ums.service.MessageService;

/**  
 * @Title: WxMessageTest.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.test   
 * @Description: 邮件发送测试类
 * @author: 王共亮     
 * @date: 2020年10月23日 上午10:58:41   
 */
@SpringBootTest
@SuppressWarnings("all")
@RunWith(SpringRunner.class)
public class EmailTest {
  //服务注册
  @Autowired
  private MessageService messageService;

  /**
  * @Title: sendEmail 
  * @Description: 发送邮件测试类
  * @throws ApiException void  
  * @author 王共亮
  * @date 2020年11月20日 下午5:16:29
   */
  @Test
  public void sendEmail() throws ApiException {
    EmailParam emailParam = new EmailParam();
    //邮件模板id-对应数据库中的模板id
    String templateId = "d46602ddfa16463cb641c642b33d6da5";
    //收件人
    String toMails = "303084687@qq.com,18911133276@189.cn,gongliang.wang@ctgtmo.com,wglhn19850416@163.com";
    //抄送人
    String ccMails = "3095210161@qq.com";
    //邮件内容,优先使用传递的值,若为空使用设置的邮件内容
    String content = "";
    //模板参数对应的值
    Map<String, Object> templateValue = new HashMap<>();
    templateValue.put("greetName", "测试邮件发送");
    templateValue.put("content", "初闻不知曲中意，再闻已是曲中人。 既然已成曲中人，何必再听曲中曲。曲终人散梦已醒，何处再寻梦中人。 既知曲人存于梦，何故执于曲外人。 不愿再做曲中人，奈何越听越沉沦。曲中曲，人中人，曲散人终离。一曲肝肠断， 天涯何处觅知音。");
    templateValue.put("sign", "唐李白");
    //邮件附件
    List<HashMap<String, Object>> fileList = new ArrayList<>();
    HashMap<String, Object> map1 = new HashMap<>();
    byte[] data1 = FileToByte(new File("D:\\commonFile\\employTemplate.xls"));
    map1.put("fileName", "xls格式文件.xls");
    map1.put("data", data1);
    fileList.add(map1);
    HashMap<String, Object> map2 = new HashMap<>();
    byte[] data2 = FileToByte(new File("D:\\commonFile\\java.docx"));
    map2.put("fileName", "docx格式文件.docx");
    map2.put("data", data2);
    fileList.add(map2);
    emailParam.setToMails(toMails);
    emailParam.setCcMails(ccMails);
    emailParam.setContent(content);
    emailParam.setFileList(fileList);
    emailParam.setTemplateId(templateId);
    emailParam.setTemplateValue(templateValue);
    // 返回和封装结果集
    CommonResult jsonObject = messageService.sendTemplateEmail(emailParam);
    Assert.assertEquals("200", String.valueOf(jsonObject.getCode()));
  }

  /**
   * 将文件转换成byte数组
   * @param filePath
   * @return
   */
  public static byte[] FileToByte(File tradeFile) {
    byte[] buffer = null;
    try {
      FileInputStream fis = new FileInputStream(tradeFile);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      byte[] b = new byte[1024];
      int n;
      while ((n = fis.read(b)) != -1) {
        bos.write(b, 0, n);
      }
      fis.close();
      bos.close();
      buffer = bos.toByteArray();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return buffer;
  }
}
