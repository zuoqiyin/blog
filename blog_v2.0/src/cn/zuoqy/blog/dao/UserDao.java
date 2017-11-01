package cn.zuoqy.blog.dao;

import java.util.Map;

import cn.zuoqy.blog.model.User;

public interface UserDao {

	public User getUser(Map<String, String> params) throws Exception;
	
	public int addUser(User user) throws Exception;
	
	public int updateUser(User user) throws Exception;
}
