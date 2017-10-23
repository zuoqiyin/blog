package cn.zuoqy.blog.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.zuoqy.blog.model.Member;
import cn.zuoqy.blog.model.Verify;
import cn.zuoqy.blog.service.MemberService;
import cn.zuoqy.blog.service.VerifyService;
import cn.zuoqy.blog.util.Application;
import cn.zuoqy.blog.util.DataBase;
import cn.zuoqy.blog.util.SendMail;
import cn.zuoqy.blog.util.StringUtil;

/**
 * @author zuoqiyin
 * @date 2017-9-20
 * @Description: userController
 * @version:1.0
 */
@Controller
public class MemberController {

	@Autowired
	private VerifyService verifyService;
	
	@Autowired
	private MemberService memberService;
	
	private static final Logger logger = Logger.getLogger("blog");
	
	/**
	 * 激活邮箱-成功显示
	 * @return
	 */
	@RequestMapping(value="/member/activateSuccess.do",method=RequestMethod.GET)
	public String activateSuccess(Model model, HttpServletRequest request) {
		model.addAttribute("email",request.getParameter("email"));
		return "activateEmail";
	}
	
	/**
	 * 激活邮箱-邮件入口
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/activateEmail.do",method=RequestMethod.GET)
	public String activateEmail(Model model, HttpServletRequest request) {
		String token = request.getParameter("token");
		String email = request.getParameter("email");
		try {
			Member member = memberService.getMemberByEmail(email);
			if (member==null) {
				model.addAttribute("code", 0);
			}
			if (token !=null && !"".equals(token) && token.equals(member.getToken())) {
				model.addAttribute("code", 2);
				model.addAttribute("email",member.getEmail());
				Map<String, String> params = new HashMap<String, String>();
				params.put("email", member.getEmail());
				params.put("isActivate","Y");
				memberService.updateMember(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sendIndex";
	}
	
	/**
	 * 发送激活邮件
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/activateEmail.do",method=RequestMethod.POST )
	@ResponseBody
	public DataBase activateEmail(HttpServletRequest request) {
		DataBase db = new DataBase();
		if (request.getSession().getAttribute(Application.MEMBER)==null) {
			db.setCode(0);
			db.setMsg("请先登录,在激活用户。");
			return db;
		}
		Member member = (Member) request.getSession().getAttribute(Application.MEMBER);
		
		//修改token值
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", member.getEmail());
		String token = StringUtil.getUUID();
		params.put("token", token);
		try {
			memberService.updateMember(params);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("error");
			return db;
		}
		
		List<String> sends = new ArrayList<String>();
		sends.add(member.getEmail());
		//发送邮件
		ResourceBundle rb = ResourceBundle.getBundle("system");
		String smtp = rb.getString("email.smtp");
		String username = rb.getString("email.username");
		String password = rb.getString("email.password");
		String path = rb.getString("contextPath");
		String title = "zuoqy博客-激活邮箱";
		String context = 
				    "<table style=\"width: 600px; border: 1px solid #ddd; border-radius: 3px; color: #555; font-family: 'Helvetica Neue Regular',Helvetica,Arial,Tahoma,'Microsoft YaHei','San Francisco','微软雅黑','Hiragino Sans GB',STHeitiSC-Light; font-size: 12px; height: auto; margin: auto; overflow: hidden; text-align: left; word-break: break-all; word-wrap: break-word;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"+
					"	<tbody style=\"margin: 0; padding: 0;\">"+
					"		<tr style=\"background-color: #393D49; height: 60px; margin: 0; padding: 0;\">"+
					"			<td style=\"margin: 0; padding: 0;\">"+
					"				<div style=\"color: #5EB576; margin: 0; margin-left: 30px; padding: 0;\">"+
					"					<a style=\"font-size: 14px; margin: 0; padding: 0; color: #5EB576; text-decoration: none;\" href=\""+path+"\" target=\"_blank\">zuoqy博客</a>"+
					"				</div>"+
					"			</td>"+
					"		</tr>"+
					"		<tr style=\"margin: 0; padding: 0;\">"+
					"			<td style=\"margin: 0; padding: 30px;\">"+
					"			<p style=\"line-height: 20px; margin: 0; margin-bottom: 10px; padding: 0;\">"+
					"				你好，"+
					"				<em style=\"font-weight: 700;\">"+member.getName()+"</em>"+
					"				童鞋，请在30分钟内激活您的邮箱："+
					"			</p>"+
					"			<div style=\"\">"+
					"				<a href=\""+path+"/member/activateEmail.do?token="+token+"&email="+member.getEmail() + "\" style=\"background-color: #009E94; color: #fff; display: inline-block; height: 32px; line-height: 32px; margin: 0 15px 0 0; padding: 0 15px; text-decoration: none;\" target=\"_blank\">立即激活邮箱</a>"+
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
		Thread t = new Send(smtp, username, password,title, context,sends);
		t.start();
		db.setCode(1);
		return db;
	}
	
	/**
	 * 上传照片
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/upload.do",method=RequestMethod.POST)
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
			Member member = (Member) request.getSession().getAttribute(Application.MEMBER);
			String path = request.getSession().getServletContext().getRealPath("/img/member");
			File upload = new File(path+File.separator+member.getMid()+fileType);
			try {
				file.transferTo(upload);
				Map<String, String> params = new HashMap<String, String>();
				params.put("img", "/member/"+member.getMid()+fileType);
				params.put("mid", member.getMid());
				memberService.updateMember(params);
				// 刷新session信息
				member = memberService.getMemberByEmail(member.getEmail());
				db.setItem(member);
				request.getSession().setAttribute(Application.MEMBER, member);
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
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/updataPwd.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase updataPwd(HttpServletRequest request) {
		DataBase db = new DataBase();
		HttpSession session = request.getSession();
		if (session.getAttribute(Application.MEMBER) != null) {
			Member member = (Member) session.getAttribute(Application.MEMBER);
			Map<String, String> params = new HashMap<String, String>();
			params.put("mid", member.getMid());
			params.put("pwd", request.getParameter("pwd"));
			try {
				memberService.updateMember(params);
				db.setCode(1);
			} catch (Exception e) {
				e.printStackTrace();
				db.setMsg("error");
				db.setCode(0);
			}
		} else {
			db.setCode(0);
			db.setMsg("您的登陆,已失效,请重新登陆");
		}
		return db;
	}
	
	/**
	 * 修改个人资料
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/updataInfo.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase updataInfo(HttpServletRequest request) {
		DataBase db = new DataBase();
		
		HttpSession session = request.getSession();
		Member UserMember = (Member) session.getAttribute(Application.MEMBER);
		if (session.getAttribute(Application.MEMBER)!=null) { 
			String email = request.getParameter("email");
			Map<String, String> params = new HashMap<String, String>();
			params.put("emailOld", email);
			params.put("sign", request.getParameter("sign"));
			params.put("city", request.getParameter("city"));
			params.put("name", request.getParameter("name"));
			
			try {
				Member member = memberService.getMemberByEmail(email);
				if (member!=null && !member.getMid().equals(UserMember.getMid())) {
					db.setCode(0);
					db.setMsg("此邮箱已经注册");
					return db;
				}
				
				if (!UserMember.getEmail().equals(email)) {//修改了邮箱，该邮箱需要重新验证
					params.put("isActivate", "N");
				}
				
				params.put("mid", UserMember.getMid());
				memberService.updateMember(params);
				// 刷新session信息
				member = memberService.getMemberByEmail(email);
				request.getSession().setAttribute(Application.MEMBER, member);
				db.setItem(member);
				db.setCode(1);
				session.setAttribute(Application.MEMBER,memberService.getMemberByEmail(email));
			} catch (Exception e) {
				e.printStackTrace();
				db.setCode(0);
				db.setMsg("error");
			}
		} else {
			db.setCode(0);
			db.setMsg("error");
		}
		return db;
	}
	
	/**
	 * 个人主页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/member/getUser.do")
	public String getUser(Model model, HttpServletRequest request) {
		model.addAttribute("member", request.getSession().getAttribute(Application.MEMBER));
		return "member";
	}
	
	/**
	 * 重置密码,最后处理
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/resetPwdDispose.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase resetPwdDispose(HttpServletRequest request) {
		DataBase db = new DataBase();
		String verify = request.getParameter("verify");
		if (request.getSession().getAttribute(Application.VERIFY)==null || !request.getSession().getAttribute(Application.VERIFY).equals(verify)) {
			db.setCode(0);
			db.setMsg("人类验证失败");
			return db;
		}
		String mid = request.getParameter("mid");
		String pwd = request.getParameter("pwd");
		Map<String, String> params = new HashMap<String, String>();
		params.put("mid", mid);
		params.put("pwd", pwd);
		params.put("token", StringUtil.getUUID());
		try {
			memberService.updateMember(params);
			db.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
		}
		return db;
	}
	
	/**
	 * 重置密码,发送邮件
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/resetPwd.do",method=RequestMethod.POST)
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
		Member member;
		try {
			member = memberService.getMemberByEmail(email);
			if (member==null) {
				db.setCode(0);
				db.setMsg("该邮箱不存在");
				return db;
			}
			//修改token值
			Map<String, String> params = new HashMap<String, String>();
			params.put("email", email);
			String token = StringUtil.getUUID();
			params.put("token", token);
			memberService.updateMember(params);
			
			List<String> sends = new ArrayList<String>();
			sends.add(email);
			//发送邮件
			ResourceBundle rb = ResourceBundle.getBundle("system");
			String smtp = rb.getString("email.smtp");
			String username = rb.getString("email.username");
			String password = rb.getString("email.password");
			String path = rb.getString("contextPath");
			String title = "zuoqy博客-找回密码";
			String context = 
					    "<table style=\"width: 600px; border: 1px solid #ddd; border-radius: 3px; color: #555; font-family: 'Helvetica Neue Regular',Helvetica,Arial,Tahoma,'Microsoft YaHei','San Francisco','微软雅黑','Hiragino Sans GB',STHeitiSC-Light; font-size: 12px; height: auto; margin: auto; overflow: hidden; text-align: left; word-break: break-all; word-wrap: break-word;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"+
						"	<tbody style=\"margin: 0; padding: 0;\">"+
						"		<tr style=\"background-color: #393D49; height: 60px; margin: 0; padding: 0;\">"+
						"			<td style=\"margin: 0; padding: 0;\">"+
						"				<div style=\"color: #5EB576; margin: 0; margin-left: 30px; padding: 0;\">"+
						"					<a style=\"font-size: 14px; margin: 0; padding: 0; color: #5EB576; text-decoration: none;\" href=\""+path+"\" target=\"_blank\">zuoqy博客</a>"+
						"				</div>"+
						"			</td>"+
						"		</tr>"+
						"		<tr style=\"margin: 0; padding: 0;\">"+
						"			<td style=\"margin: 0; padding: 30px;\">"+
						"			<p style=\"line-height: 20px; margin: 0; margin-bottom: 10px; padding: 0;\">"+
						"				你好，"+
						"				<em style=\"font-weight: 700;\">"+member.getName()+"</em>"+
						"				童鞋，请在30分钟内重置您的密码："+
						"			</p>"+
						"			<div style=\"\">"+
						"				<a href=\""+path+"/member/resetPwd.do?token="+token+"&email="+email + "\" style=\"background-color: #009E94; color: #fff; display: inline-block; height: 32px; line-height: 32px; margin: 0 15px 0 0; padding: 0 15px; text-decoration: none;\" target=\"_blank\">立即重置密码</a>"+
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
			Thread t = new Send(smtp, username, password,title, context,sends);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("发送邮件错误,管理员正在修复!");
		}
		db.setCode(1);
		return db;
	}
	
	/**
	 * 发送邮件
	 * @author zuoqiyin
	 * @date 2017-10-19
	 * @Description: 
	 * @version:1.0
	 */
	class Send extends Thread{
		String smtp,username,password,title,context;
		List<String> sends;
		
		public Send(String smtp, String username, String password,
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
			SendMail sm = new SendMail();
			try {
				sm.sendMail(smtp, username, password, sends, title, context,"text/html;charset=UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(sends+"邮件发送失败");
			}
		}
	}
	
	/**
	 * 重置密码-邮件入口
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/resetPwd.do",method=RequestMethod.GET)
	public String resetPwd(Model model, HttpServletRequest request) {
		String token = request.getParameter("token");
		String email = request.getParameter("email");
		try {
			Member member = memberService.getMemberByEmail(email);
			if (member==null) {
				model.addAttribute("code", 0);
			}
			if (token !=null && !"".equals(token) && token.equals(member.getToken())) {
				model.addAttribute("code", 1);
				model.addAttribute("mid", member.getMid());
				model.addAttribute("name",member.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("email", email);
		model.addAttribute("token",token);
		return "sendIndex";
	}
	
	/**
	 * 重置密码跳页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/member/resetPwdPage.do",method=RequestMethod.GET)
	public String resetPwdPage(Model model, HttpServletRequest request) {
		Verify ver = null;
		String token = request.getParameter("token");//为了安全还是加一个token验证,实际功能可不要
		String email = request.getParameter("email");
		try {
			Member member = memberService.getMemberByEmail(email);
			if (member==null) {
				model.addAttribute("code", 0);
			}
			if (token !=null && !"".equals(token) && token.equals(member.getToken())) {
				model.addAttribute("code", 1);
				model.addAttribute("mid", member.getMid());
				model.addAttribute("name",member.getName());
			}
			ver = verifyService.getVerify();
			model.addAttribute("verify", ver.getQuestion());
			request.getSession().setAttribute(Application.VERIFY, ver.getAnswer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "resetPwd";
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/reg.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase reg(HttpServletRequest request){
		DataBase base = new DataBase();
		
		String verify = request.getParameter("verify");
		if (request.getSession().getAttribute(Application.VERIFY)==null || !request.getSession().getAttribute(Application.VERIFY).equals(verify)) {
			base.setCode(0);
			base.setMsg("人类验证失败");
			return base;
		}
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		try {
			Member member = memberService.getMemberByEmail(email);
			if (member!=null) {
				base.setCode(0);
				base.setMsg("此邮箱已经注册");
				return base;
			}
			member = memberService.getMemberByName(name);
			if (member!=null) {
				base.setCode(0);
				base.setMsg("此昵称已存在");
				return base;
			}
			member = new Member();
			member.setEmail(email);
			member.setName(name);
			member.setPwd(pwd);
			member.setMid(StringUtil.getUUID());
			ResourceBundle rb = ResourceBundle.getBundle("system");
			int num = new Random().nextInt(Integer.parseInt(rb.getString("imgCount")));
			member.setImg("/member/"+num+".jpg");
			int count = memberService.addMember(member);
			if (count>0) {
				base.setCode(1);
				base.setItem(member);
				request.getSession().setAttribute(Application.MEMBER, member);
				logger.error("用户:"+member.getEmail()+",注册了");
			}
		} catch (Exception e) {
			e.printStackTrace();
			base.setCode(0);
			base.setMsg("未知错误");
		}
		return base;
	}
	
	/**
	 * 登陆操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase login(HttpServletRequest request){
		DataBase data = new DataBase();
		String verify = request.getParameter("verify");
		if (request.getSession().getAttribute(Application.VERIFY)==null || !request.getSession().getAttribute(Application.VERIFY).equals(verify)) {
			data.setCode(0);
			data.setMsg("人类验证失败");
			return data;
		}
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		try {
			Member member = memberService.login(email, pwd);
			if (member==null) {
				data.setCode(0);
				data.setMsg("用户名或密码错误");
				return data;
			} else {
				data.setCode(1);
				data.setItem(member);
				request.getSession().setAttribute(Application.MEMBER, member);
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 退出
	 * @param request
	 * @return
	 */
	@RequestMapping("/member/exit.do")
	@ResponseBody
	public DataBase exitUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Application.MEMBER);
		DataBase db = new DataBase();
		db.setCode(1);
		return db;
	}
	
	/**
	 * 登陆跳页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/member/loginPage.do")
	public String login(Model model,HttpServletRequest request) {
		model.addAttribute("flag", "login");
		//获得验证码
		Verify ver = null;
		try {
			ver = verifyService.getVerify();
			model.addAttribute("verify", ver.getQuestion());
			request.getSession().setAttribute(Application.VERIFY, ver.getAnswer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "login";
	}
	
	/**
	 * 注册跳页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/member/regPage.do")
	public String reg(Model model,HttpServletRequest request) {
		model.addAttribute("flag", "reg");
		
		//获得验证码
		Verify ver = null;
		try {
			ver = verifyService.getVerify();
			model.addAttribute("verify", ver.getQuestion());
			request.getSession().setAttribute(Application.VERIFY, ver.getAnswer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "login";
	}
}
