package cn.zuoqy.blog.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

/**
 * @author zuoqiyin
 * @date 2017-10-28
 * @Description: 开启一个线程发送邮件
 * @version:1.0
 */
public class SendMail extends Thread{
	private static final Logger logger = Logger.getLogger("blog");
	
	String smtp,username,password,title,context;
	List<String> sends;
	
	public SendMail(String smtp, String username, String password,
			String title, String context, List<String> sends) {
		super();
		this.smtp = smtp;
		this.username = username;
		this.password = password;
		this.title = title;
		this.context = context;
		this.sends = sends;
	}
	
	
	@Override
	public void run() {
		try {
			sendMail(smtp, username, password, sends, title, context,"text/html;charset=UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(sends+"邮件发送失败");
		}
	}
	
	/**
	 * 
	 * @param smtp 传输协议
	 * @param username 发件人账号
	 * @param password 发件人密码
	 * @param sends 收件人
	 * @param title 标题
	 * @param context 内容
	 * @param charset 发送类型
	 * @throws Exception
	 */
	public void sendMail(String smtp, String username, String password, List<String> sends, String title, String context, String charset) throws Exception {
			//配置发送邮件的环境属性
			final Properties props = new Properties();
			//表示SMTP发送邮件，需要进行身份验证
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.host", smtp);
			//发件人的账号
			props.put("mail.user", username);
			//访问SMTP服务时需要提供的密码
			props.put("mail.password", password);
			
			//邮箱发送服务器端口,这里设置为465端口
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");
	        props.setProperty("mail.smtp.port", "465");
	        props.setProperty("mail.smtp.socketFactory.port", "465");
			
			//构建授权信息，用于进行SMTP进行身份验证
			Authenticator authenticate = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication(){
					//用户名、密码
					String userName = props.getProperty("mail.user");
					String password = props.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			
			};
			
			//使用环境属性和授权信息，创建邮件会话
			Session mailSession = Session.getInstance(props,authenticate);
			//创建邮件消息
			MimeMessage message = new MimeMessage(mailSession);
			//设置发件人
			InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
			message.setFrom(form);
			
			for	(int i=0; i<sends.size(); i++) {
				//设置收件人
				InternetAddress to = new InternetAddress(sends.get(i));
				message.setRecipient(RecipientType.TO, to);
				//设置邮件标题
				message.setSubject(title);
				//设置邮件的内容
				message.setContent(context, charset);
				//发送邮件
				Transport.send(message);
			}
	}
}
