package cn.zuoqy.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuoqy.blog.dao.MessageDao;
import cn.zuoqy.blog.model.Message;
import cn.zuoqy.blog.service.ArticleService;
import cn.zuoqy.blog.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao msgDao;
	
	@Autowired
	private ArticleService artService;
	
	@Override
	public List<Message> selectMessage(Map<String, Object> params)
			throws Exception {
		return msgDao.selectMessage(params);
	}

	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return msgDao.getCount(params);
	}

	@Override
	public Message getMessage(String mid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mid", mid);
		return msgDao.getMessage(params);
	}

	@Override
	public int updateMessage(Map<String, Object> params) throws Exception {
		return msgDao.updateMessage(params);
	}

	@Override
	public int addMessage(Message msg) throws Exception {
		if ("".equals(msg.getIsYe()) && !"0".equals(msg.getAid()))
			artService.addCom(msg.getAid());
		
		return msgDao.addMessage(msg);
	}

}
