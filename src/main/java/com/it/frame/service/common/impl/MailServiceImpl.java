package com.it.frame.service.common.impl;

import com.it.frame.service.common.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 邮件服务实现类
 *
 * @author chenshaoqi
 * @since 2020/5/25
 */
@Slf4j
public class MailServiceImpl implements MailService {


//    @Value("${mail.username}")
//    private String username;

//    @Resource
//    private JavaMailSender javaMailSender;

//    @Async
//    @Override
//    public void syncSendMail(String subject, String content, String... to) {
//        if (StringUtils.isEmpty(subject) || StringUtils.isEmpty(content) || null == to) {
//            return;
//        }
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("username");
//        simpleMailMessage.setTo(to);
//        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(content);
//        try {
//            javaMailSender.send(simpleMailMessage);
//            log.info("成功发送邮件到 {} ", Arrays.toString(to));
//        } catch (MailException e) {
//            log.error("发送邮件到 {} 失败，异常为：",  Arrays.toString(to), e);
//        }
//    }

//    import javax.mail.internet.MimeMessage;
//    @Async
//    @Override
//    public void syncSendMailWithAttach(String subject, String content, File file, String... to) {
//        if (StringUtils.isEmpty(subject) || StringUtils.isEmpty(content) || null == to) {
//            return;
//        }
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        try {
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom(username);
//            mimeMessageHelper.setTo(to);
//            mimeMessageHelper.setSubject(subject);
//            mimeMessageHelper.setText(content);
//            mimeMessageHelper.addAttachment(file.getName(), file);
//            javaMailSender.send(mimeMessage);
//            log.info("成功发送邮件到 {} ", Arrays.toString(to));
//        } catch (Exception e) {
//            log.error("发送邮件到 {} 失败，异常为：",  Arrays.toString(to), e);
//        }
//    }
}
