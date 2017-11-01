package cn.zuoqy.blog.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.zuoqy.blog.model.Dict;
import cn.zuoqy.blog.model.User;
import cn.zuoqy.blog.service.DictService;
import cn.zuoqy.blog.service.UserService;
import cn.zuoqy.blog.util.Application;
import cn.zuoqy.blog.util.DataBase;
import cn.zuoqy.blog.util.SendMail;
import cn.zuoqy.blog.util.StringUtil;

@Controller
public class UserController {

	@Autowired
	private DictService dictService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 激活邮箱
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/usre/activeEmail.do")
	@ResponseBody
	public DataBase activeEmail(HttpServletRequest request) {
		DataBase db = new DataBase();
		String uid = request.getParameter("uid");
		String token = request.getParameter("token");
		User user;
		try {
			user = userService.getUserByUid(uid);
			if (user==null || !user.getToken().equals(token)) {
				db.setCode(0);
				db.setMsg("请求异常,请稍后重试!");
				return db;
			}
			user.setIsActive("Y");
			userService.updateUser(user);
			db.setCode(1);
		} catch (Exception e) {
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;
	}
	
	/**
	 * 激活邮箱-发送邮件
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/usre/activeEmail/send.do")
	@ResponseBody
	public DataBase activeSendEmail(HttpServletRequest request) {
		DataBase db = new DataBase();
		String uid = request.getParameter("uid");
		String token = request.getParameter("token");
		User user;
		try {
			user = userService.getUserByUid(uid);
			if (user==null || !user.getToken().equals(token)) {
				db.setCode(0);
				db.setMsg("请求异常,请稍后重试!");
				return db;
			}
			List<String> sends = new ArrayList<String>();
			sends.add(user.getEmail());
			//发送邮件
			ResourceBundle rb = ResourceBundle.getBundle("system");
			String smtp = rb.getString("email.smtp");
			String username = rb.getString("email.username");
			String password = rb.getString("email.password");
			String path = rb.getString("contextPath");
			String title = "zuoqy博客-一个java程序员的个人博客网站";
			String context = 
					    "<table style=\"width: 600px; border: 1px solid #ddd; border-radius: 3px; color: #555; font-family: 'Helvetica Neue Regular',Helvetica,Arial,Tahoma,'Microsoft YaHei','San Francisco','微软雅黑','Hiragino Sans GB',STHeitiSC-Light; font-size: 12px; height: auto; margin: auto; overflow: hidden; text-align: left; word-break: break-all; word-wrap: break-word;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"+
						"	<tbody style=\"margin: 0; padding: 0;\">"+
						"		<tr style=\"background-color: #393D49; height: 60px; margin: 0; padding: 0;\">"+
						"			<td style=\"margin: 0; padding: 0;\">"+
						"				<div style=\"color: #5EB576; margin: 0; margin-left: 30px; padding: 0;\">"+
						"					<a style=\"font-size: 14px; margin: 0; padding: 0; color: #5EB576; text-decoration: none;\" href=\""+path+"\" target=\"_blank\">zuoqy博客-激活邮箱</a>"+
						"				</div>"+
						"			</td>"+
						"		</tr>"+
						"		<tr style=\"margin: 0; padding: 0;\">"+
						"			<td style=\"margin: 0; padding: 30px;\">"+
						"			<p style=\"line-height: 20px; margin: 0; margin-bottom: 10px; padding: 0;\">"+
						"				你好，"+
						"				<em style=\"font-weight: 700;\">"+user.getName()+"</em>"+
						"				童鞋，请在30分钟内重置您的密码："+
						"			</p>"+
						"			<div style=\"\">"+
						"				<a href=\""+path+"/activeEmail.html?token="+user.getToken()+"&uid="+user.getUid()+"&email="+user.getEmail() + "\" style=\"background-color: #009E94; color: #fff; display: inline-block; height: 32px; line-height: 32px; margin: 0 15px 0 0; padding: 0 15px; text-decoration: none;\" target=\"_blank\">立即重置密码</a>"+
						"			</div>"+
						"			<p style=\"line-height: 20px; margin-top: 20px; padding: 10px; background-color: #f2f2f2; font-size: 12px;\"> 如果该邮件不是由你本人操作，请勿操作！否则你的邮箱可能存在安全隐患。 </p>"+
						"			</td>"+
						"		</tr>"+
						"		<tr style=\"background-color: #fafafa; color: #999; height: 35px; margin: 0; padding: 0; text-align: center;\">"+
						"			<td style=\"margin: 0; padding: 0;\">系统邮件，请勿直接回复。</td>"+
						"		</tr>"+
						"	</tbody>"+
						"</table>"+
						"<style type=\"text/css\">"+
						".qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}"+
						"</style>";
			Thread t = new SendMail(smtp, username, password, title, context, sends);
			t.start();
			db.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;
	}
	
	/**
	 * 重置密码,发送邮件
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/resetPwd/send.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase resetPwd(HttpServletRequest request) {
		DataBase db = new DataBase();
		String verify = request.getParameter("verify");
		if (request.getSession().getAttribute(Application.VERIFY)==null || !request.getSession().getAttribute(Application.VERIFY).equals(verify)) {
			db.setCode(0);
			db.setMsg("人类验证失败");
			return db;
		}
		String email = request.getParameter("email");
		User user;
		try {
			user = userService.getUserByEmail(email);
			if (user==null) {
				db.setCode(0);
				db.setMsg("该邮箱不存在");
				return db;
			}
			
			List<String> sends = new ArrayList<String>();
			sends.add(email);
			//发送邮件
			ResourceBundle rb = ResourceBundle.getBundle("system");
			String smtp = rb.getString("email.smtp");
			String username = rb.getString("email.username");
			String password = rb.getString("email.password");
			String path = rb.getString("contextPath");
			String title = "zuoqy博客-一个java程序员的个人博客网站";
			String context = 
					    "<table style=\"width: 600px; border: 1px solid #ddd; border-radius: 3px; color: #555; font-family: 'Helvetica Neue Regular',Helvetica,Arial,Tahoma,'Microsoft YaHei','San Francisco','微软雅黑','Hiragino Sans GB',STHeitiSC-Light; font-size: 12px; height: auto; margin: auto; overflow: hidden; text-align: left; word-break: break-all; word-wrap: break-word;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"+
						"	<tbody style=\"margin: 0; padding: 0;\">"+
						"		<tr style=\"background-color: #393D49; height: 60px; margin: 0; padding: 0;\">"+
						"			<td style=\"margin: 0; padding: 0;\">"+
						"				<div style=\"color: #5EB576; margin: 0; margin-left: 30px; padding: 0;\">"+
						"					<a style=\"font-size: 14px; margin: 0; padding: 0; color: #5EB576; text-decoration: none;\" href=\""+path+"\" target=\"_blank\">zuoqy博客-找回密码</a>"+
						"				</div>"+
						"			</td>"+
						"		</tr>"+
						"		<tr style=\"margin: 0; padding: 0;\">"+
						"			<td style=\"margin: 0; padding: 30px;\">"+
						"			<p style=\"line-height: 20px; margin: 0; margin-bottom: 10px; padding: 0;\">"+
						"				你好，"+
						"				<em style=\"font-weight: 700;\">"+user.getName()+"</em>"+
						"				童鞋，请在30分钟内重置您的密码："+
						"			</p>"+
						"			<div style=\"\">"+
						"				<a href=\""+path+"/resetPwd.html?token="+user.getToken()+"&uid="+user.getUid()+"&name="+user.getName() + "\" style=\"background-color: #009E94; color: #fff; display: inline-block; height: 32px; line-height: 32px; margin: 0 15px 0 0; padding: 0 15px; text-decoration: none;\" target=\"_blank\">立即重置密码</a>"+
						"			</div>"+
						"			<p style=\"line-height: 20px; margin-top: 20px; padding: 10px; background-color: #f2f2f2; font-size: 12px;\"> 如果该邮件不是由你本人操作，请勿操作！否则你的邮箱可能存在安全隐患。 </p>"+
						"			</td>"+
						"		</tr>"+
						"		<tr style=\"background-color: #fafafa; color: #999; height: 35px; margin: 0; padding: 0; text-align: center;\">"+
						"			<td style=\"margin: 0; padding: 0;\">系统邮件，请勿直接回复。</td>"+
						"		</tr>"+
						"	</tbody>"+
						"</table>"+
						"<style type=\"text/css\">"+
						".qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}"+
						"</style>";
			Thread t = new SendMail(smtp, username, password, title, context, sends);
			t.start();
			db.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		
		return db;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/set/pwd.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase updatePwd(HttpServletRequest request) {
		DataBase db = new DataBase();
		String token = request.getParameter("token");
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		User user;
		try {
			user = userService.getUserByUid(uid);
			if (user==null || !user.getToken().equals(token)) {
				db.setCode(0);
				db.setMsg("error");
				return db;
			}
			user.setPwd(pwd);
			userService.updateUser(user);
			db.setItem(user);
			db.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;
	}
	
	/**
	 * 上传照片
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/upload.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		DataBase db = new DataBase();
		if (file.getSize()>51200) {
			db.setCode(0);
			db.setMsg("文件大小超出限制");
			return db;
		}
		String originalFilename  = file.getOriginalFilename();
		String fileType = originalFilename.substring(originalFilename.lastIndexOf('.'));
		if (".jpg".equalsIgnoreCase(fileType) || ".png".equalsIgnoreCase(fileType) || ".gif".equalsIgnoreCase(fileType)) {
			String uid = request.getParameter("uid");
			String token = request.getParameter("token");
			try {
				User user = userService.getUserByUid(uid);
				if (user==null || !user.getToken().equals(token)) {
					db.setCode(0);
					db.setMsg("error");
					return db;
				}
				
				String path = request.getSession().getServletContext().getRealPath("/images/user/");
				File upload = new File(path+File.separator+uid+fileType);
			
				file.transferTo(upload);
				user.setImg("/user/"+uid+fileType);
				userService.updateUser(user);
				// 刷新session信息
				db.setItem(user);
				request.getSession().setAttribute(Application.USER, user);
				db.setCode(1);
			} catch (Exception e) {
				e.printStackTrace();
				db.setCode(0);
				db.setMsg("error");
			}
		} else {
			db.setCode(0);
			db.setMsg("非jpg,png,gif类型文件,不允许上传");
		}
		return db;
	}
	
	/**
	 * 修改个人信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/set/info.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase updateUser(HttpServletRequest request) {
		DataBase db = new DataBase();
		User user = new User();
		user.setToken(request.getParameter("token"));
		user.setUid(request.getParameter("uid"));
		user.setName(request.getParameter("name"));
		user.setSex(request.getParameter("sex"));
		user.setCity(request.getParameter("city"));
		user.setSign(request.getParameter("sign"));
		user.setEmail(request.getParameter("email"));
		try {
			User oldUser = userService.getUserByUid(user.getUid());
			if (oldUser==null || !oldUser.getToken().equals(user.getToken())) {
				db.setCode(0);
				db.setMsg("请求异常,请稍后重试!");
				return db;
			}
			if (!oldUser.getEmail().equals(user.getEmail())) {
				user.setIsActive("N");
			}
			userService.updateUser(user);
			user = userService.getUserByUid(user.getUid());
			request.getSession().setAttribute(Application.USER, user);
			db.setCode(1);
			db.setItem(user);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;		
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/register.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase register(HttpServletRequest request) {
		DataBase db = new DataBase();
		String verify = request.getParameter("verify");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		// 人类验证
		if (request.getSession().getAttribute(Application.VERIFY)==null && !request.getSession().getAttribute(Application.VERIFY).equals(verify)) {
			db.setCode(0);
			db.setMsg("人类验证错误");
			return db;
		}
		try {
			if (userService.getUserByEmail(email)!=null) {
				db.setCode(0);
				db.setMsg("该email已存在");
				return db;
			}
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPwd(pwd);
			user.setToken(StringUtil.getUUID());
			userService.addUser(user);
			db.setCode(1);
			user = userService.getUserByEmail(email);
			db.setItem(user);
			request.getSession().setAttribute(Application.USER, user);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;
	}
	
	/**
	 * 登陆
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/login.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase login(HttpServletRequest request) {
		DataBase db = new DataBase();
		String verify = request.getParameter("verify");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		if (request.getSession().getAttribute(Application.VERIFY)==null && !request.getSession().getAttribute(Application.VERIFY).equals(verify)) {
			db.setCode(0);
			db.setMsg("人类验证错误");
			return db;
		}
		
		User user;
		try {
			user = userService.getUserByEmail(email);
			if (user==null) {
				db.setCode(0);
				db.setMsg("该用户名不存在");
				return db;
			}
			// 登陆成功
			if (user.getPwd().equals(pwd)) {
				db.setCode(1);
				user.setToken(StringUtil.getUUID());
				userService.updateToken(user.getUid(), user.getToken());
				db.setItem(user);
				request.getSession().setAttribute(Application.USER, user);
			} else {
				db.setCode(0);
				db.setMsg("密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/verify.do",method=RequestMethod.GET)
	@ResponseBody
	public DataBase getVerify(HttpServletRequest request) {
		DataBase db = new DataBase();
		try {
			Dict dict = dictService.getVerify();
			db.setCode(1);
			db.setItem(dict.getItemCode());
			request.getSession().setAttribute(Application.VERIFY, dict.getItemName());
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;
	}
}
