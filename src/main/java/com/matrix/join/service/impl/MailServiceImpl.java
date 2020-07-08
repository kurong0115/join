package com.matrix.join.service.impl;

import com.matrix.join.constant.MailConstant;
import com.matrix.join.service.MailService;
import com.matrix.join.util.RedisUtil;
import com.matrix.join.util.SecretUtils;
import com.matrix.join.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MailServiceImpl
 * @Description 邮件服务实现类
 * @Author Administrator
 * @Date 2019/11/29 10:22
 * @Version 1.0
 */
@Service
public class MailServiceImpl implements MailService {

	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

    private RedisUtil redisUtil;

    @Autowired
	public MailServiceImpl(JavaMailSender mailSender, RedisUtil redisUtil) {
		this.mailSender = mailSender;
		this.redisUtil = redisUtil;
	}

	@Async
	@Override
	public void sendAttachMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper=new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content);
			FileSystemResource file=new FileSystemResource(new File(filePath));
			String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
			//添加多个附件可以使用多条
			//helper.addAttachment(fileName,file);
			helper.addAttachment(fileName,file);
			mailSender.send(message);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Async
	@Override
	public void sendRecoveryEmail(String email) {
        String emailKey = StringUtils.concat(email, MailConstant.RECOVERY);
        String secret = SecretUtils.getUUID();
        String text = StringUtils.concat(MailConstant.RECOVERY_PREFIX,
                MailConstant.PROJECT_PATH, email, "&secret=", secret, MailConstant.SUFFIX);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(MailConstant.RECOVERY_SUBJECT);
        message.setTo(email);
        message.setText(text);
        redisUtil.setEx(emailKey, secret, MailConstant.TIME_OUT, TimeUnit.MINUTES);
        mailSender.send(message);
	}

	@Async
	@Override
	public void sendSimpleMessage(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setSubject(subject);
		message.setTo(to);
		message.setText(content);
		mailSender.send(message);
	}

    @Async
    @Override
    public void sendSimpleMessage(String to) {
	    String emailKey = StringUtils.concat(to, MailConstant.REGISTER);
		String verification = SecretUtils.generateCode();
//        StringBuilder stringBuilder = new StringBuilder();
//	    stringBuilder.append(MailConstant.REGISTER_PREFIX).append(verification)
//                .append(MailConstant.SUFFIX);
	    String text = StringUtils.concat(MailConstant.REGISTER_PREFIX, verification, MailConstant.SUFFIX);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(MailConstant.REGISTER_SUBJECT);
        message.setTo(to);
        message.setText(text);
        redisUtil.setEx(emailKey, verification, MailConstant.TIME_OUT, TimeUnit.MINUTES);
        mailSender.send(message);
    }
}
