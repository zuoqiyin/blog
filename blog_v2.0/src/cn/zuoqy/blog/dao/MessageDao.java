package cn.zuoqy.blog.dao;

import java.util.List;
import java.util.Map;

import cn.zuoqy.blog.model.Message;

/**
 * @author zuoqiyin
 * @date 2017-11-2
 * @Description: messageDao
 * @version:1.0
 */
public interface MessageDao {

	public List<Message> selectMessage(Map<String, Object> params) throws Exception;
	
	public int getCount(Map<String, Object> params) throws Exception;
	
	public Message getMessage(Map<String, Object> params) throws Exception;
	
	public int updateMessage(Map<String, Object> params) throws Exception;
	
	public int addMessage(Message msg) throws Exception;
}
