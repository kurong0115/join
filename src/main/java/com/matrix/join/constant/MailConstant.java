package com.matrix.join.constant;

/**
 * @ClassName MailConstant
 * @Description 邮件发送常量
 * @Author Administrator
 * @Date 2020/2/3 10:42
 * @Version 1.0
 */
public class MailConstant {

    public static final String REGISTER_SUBJECT = "JOBYA用户注册验证码";

    public static final String REGISTER_PREFIX = "您收到的用于注册的验证码为：";

    public static final String SUFFIX = "，该验证码五分钟内有效，为了防止您的信息被盗，请妥善保护好您的个人信息";

    public static final String REGISTER = "register";

    public static final long TIME_OUT = 5;

    public static final String ATTACH_SUBJECT = "JOBYA附件简历投递";

    public static final String ATTACH_CONTENT = "您已收到附件简历，请尽快完成简历初筛！！！！！！！！！";

    public static final String RECOVERY = "recovery";

    public static final String RECOVERY_SUBJECT = "JOBYA用户重置密码";

    public static final String RECOVERY_PREFIX = "您收到的用于重置密码的链接：";

    public static final String PROJECT_PATH = "http://120.24.48.43:8080/jobya/recovery_passward.html?email=";
}
