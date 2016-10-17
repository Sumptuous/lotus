package com.lotus.common.email;

import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.MailException;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Authenticator;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author wyy.
 */
@Service
public class EmailServiceImpl implements EmailService {

    private Mailer mailer;

    @Value("${email.host}")
    private String emailHost;

    @Value("${email.port}")
    private int emailPort;

    @Value("${email.username}")
    private String emailUsername;

    @Value("${email.password}")
    private String emailPassword;

    @Value("${email.subjectPrefix}")
    private String subjectPrefix;

    @Value("${email.lotusSystemChineseName}")
    private String lotusSystemChineseName;

    private VelocityEngine ve;

    @PostConstruct
    public void init() throws GeneralSecurityException {
        ve = new VelocityEngine();
        ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"); // 设置类路径加载模板
        ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");// 设置输入字符集
        ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");// 设置输出字符集
        try {
            ve.init();// 初始化模板引擎
        } catch (Exception e) {
            e.printStackTrace();
        }

        mailer = new Mailer(emailHost, emailPort, emailUsername, emailPassword, TransportStrategy.SMTP_SSL);
    }

    public int sendEmail(String username, String emailName, String subject, String template, HashMap<String, Object> content) {
        DateTime dateTime = new DateTime();
        content.put("lotusSystemChineseName", lotusSystemChineseName);
        content.put("date", dateTime.toString("yyyy-MM-dd HH:mm"));

        template = "template/" + template + ".vm";
        Template t = null;// 加载模板，相对于classpath路径
        try {
            t = ve.getTemplate(template);
        } catch (Exception e) {
            e.printStackTrace();
        }
        VelocityContext context = new VelocityContext();

        context.put("map", content);

        StringWriter writer = new StringWriter();
        t.merge(context, writer); // 转换

        Email email = new Email();
        email.setSubject(subjectPrefix + "-" + subject);
        email.setFromAddress(subjectPrefix + "-" + subject, emailUsername);
        email.addRecipient(emailName.split("@")[0], emailName, Message.RecipientType.TO);
        email.setTextHTML(writer.toString());//形成最终结果

        //郵件最多重試三次
        for (int i = 0; i < 3; i++) {
            try {
                mailer.sendMail(email);
                break;
            } catch (MailException e) {
                e.printStackTrace();
                //====是否需要記錄到數據庫中====
                if (i == 2) {
                    return -1;
                }
            }
        }

        return 1;
    }
}
