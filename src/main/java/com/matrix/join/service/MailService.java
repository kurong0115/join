package com.matrix.join.service;

/**
 * @ClassName MailService
 * @Description 邮件发送
 * @Author Administrator
 * @Date 2019/11/29 10:21
 * @Version 1.0
 */
public interface MailService {

	/**
	 * 发送带附件的邮件
	 * @param to 收件人
	 * @param subject 主题
	 * @param content 内容
	 * @param filePath 附件路径
	 */
	void sendAttachMail(String to, String subject, String content, String filePath);

	/**
	 * 发送简单邮件
	 * @param to 收件人
	 * @param subject 主题
	 * @param content 内容
	 */
	void sendSimpleMessage(String to, String subject, String content);

	/**
	 * 发送简单邮件
	 * @param to 发件人
	 */
	void sendSimpleMessage(String to);
}
