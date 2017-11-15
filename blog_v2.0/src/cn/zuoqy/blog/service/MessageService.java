package cn.zuoqy.blog.service;

import java.util.List;
import java.util.Map;

import cn.zuoqy.blog.model.Message;

/**
 * @author zuoqiyin
 * @date 2017-11-2
 * @Description: messageService
 * @version:1.0
 */
public interface MessageService {

	public List<Message> selectMessage(Map<String, Object> params) throws Exception;
	
	public int getCount(Map<String, Object> params) throws Exception;
	
	public Message getMessage(String mid) throws Exception;
	
	public int updateMessage(Map<String, Object> params) throws Exception;
	
	public int addMessage(Message msg) throws Exception;
}
