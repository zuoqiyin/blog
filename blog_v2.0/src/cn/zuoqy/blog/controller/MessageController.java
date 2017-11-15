package cn.zuoqy.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zuoqy.blog.model.Message;
import cn.zuoqy.blog.service.MessageService;
import cn.zuoqy.blog.util.DataBase;
import cn.zuoqy.blog.util.StringUtil;


/**
 * @author zuoqiyin
 * @date 2017-11-2
 * @Description: messageController
 * @version:1.0
 */
@Controller
public class MessageController {

	@Autowired
	private MessageService msgService;
	
	/**
	 * 点赞
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/msg/dzan.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase dzan(HttpServletRequest request){
		DataBase db = new DataBase();
		Map<String,Object> params  = new HashMap<String, Object>();
		params.put("mid", request.getParameter("mid"));
		params.put("dzan", request.getParameter("dzan"));
		try {
			msgService.updateMessage(params);
			db.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;
	}
	/**
	 * 评论
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/msg/set.do",method=RequestMethod.POST)
	@ResponseBody
	public DataBase addMsgs(HttpServletRequest request) {
		DataBase db = new DataBase();
		Message msg = new Message();
		msg.setMid(StringUtil.getUUID());
		msg.setAid(request.getParameter("aid"));
		msg.setContent(request.getParameter("content"));
		msg.setDzan("0");
		msg.setIsYe(request.getParameter("isYe"));
		msg.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		msg.setUid(request.getParameter("uid"));
		try {
			msgService.addMessage(msg);
			db.setItem(msg);
			db.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试");
		}
		return db;
	}
	
	/**
	 * 获取评论列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/msg/get.do")
	@ResponseBody
	public DataBase getMsgs(HttpServletRequest request) {
		DataBase db = new DataBase();
		int size = request.getParameter("size")!=null ? Integer.parseInt(request.getParameter("size")) : 1;
		int page = request.getParameter("page")!=null ? Integer.parseInt(request.getParameter("page")) : 1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("index", (page-1)*size);
		params.put("size", size);
		params.put("aid", request.getParameter("aid"));
		params.put("isYe","");
		try {
			List<Message> msgs = msgService.selectMessage(params);
			int count = msgService.getCount(params);
			db.setCount(count/size==0 ? count/size : count/size+1);
			db.setItem(msgs);
			db.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return db;
	}
}
