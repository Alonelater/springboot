package com.springboot10task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot10TaskApplicationTests {




    @Autowired
    private JavaMailSender sender;


    /**
     *
     * 开发步骤：
     * 第一步：通过 SimpleMailMessage 设置发送邮件信息，具体信息如下：
     * 发送人（From）
     * 被发送人（To）
     * 主题（Subject）
     * 内容（Text）
     */

    @Test
    void contextLoads() {

        //创建一个简单邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("这是一封简单的邮件");//设置主题
        simpleMailMessage.setFrom("972042778@qq.com");
        simpleMailMessage.setTo("972042778@qq.com");

        simpleMailMessage.setText("helloaaaa");//设置邮件内容
        sender.send(simpleMailMessage);

    }

    /**
     *
     * 开发步骤：
     * 第一步：通过JavaMailSender 的 createMimeMessage() 创建 MimeMessage 对象实例
     * 第二步：将 MimeMessage 放入到MimeMessageHelper 构造函数中，并通过MimeMessageHelper 设置发送邮件信息。（发送人， 被发送人，主题，内容）
     * 第三步：通过JavaMailSender send(MimeMessage mimeMessage)发送邮件。
     * 22405
     */

    @Test
    public void test02() throws MessagingException {
        //创建 MimeMessage 对象实例
        MimeMessage mimeMessage = sender.createMimeMessage();

        //是否是文件上传
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        //设置邮件信息
        helper.setFrom("972042778@qq.com");
        helper.setTo("972042778@qq.com");
        helper.setSubject("这是一封复杂的邮件");
        //开启是否需要html格式的邮件
        helper.setText("<h1>这是大标题</h1>",true);
        //设置发送附件
        helper.addAttachment("1.jpg",new File("D:\\电脑壁纸\\38d1539b428cf9a9484eb99944e828f5.jpg"));
        helper.addAttachment("2.jpg",new File("D:\\电脑壁纸\\83adc79750c490b588ec222b1fbd7750.jpg"));
        helper.addAttachment("3.pdf",new File("D:\\资料\\SpringBoot高级.pdf"));
        helper.addAttachment("4.pdf",new FileSystemResource(new File("D:\\资料\\SpringBoot高级.pdf")));
        //发送邮件
        sender.send(mimeMessage);

    }

}
