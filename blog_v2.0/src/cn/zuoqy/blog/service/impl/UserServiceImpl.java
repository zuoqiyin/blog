package cn.zuoqy.blog.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuoqy.blog.dao.UserDao;
import cn.zuoqy.blog.model.User;
import cn.zuoqy.blog.service.UserService;
import cn.zuoqy.blog.util.StringUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByUid(String uid) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", uid);
		return userDao.getUser(params);
	}

	@Override
	public User getUserByEmail(String email) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		return userDao.getUser(params);
	}

	@Override
	public int addUser(User user) throws Exception {
		ResourceBundle rb = ResourceBundle.getBundle("system");
		int vid = new Random().nextInt(Integer.parseInt(rb.getString("imgCount")));
		String img = "/user/"+vid+".jpg";
		user.setImg(img);
		user.setUid(StringUtil.getUUID());
		return userDao.addUser(user);
	}

	@Override
	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}

	@Override
	public int updateToken(String uid, String token) throws Exception {
		User user = new User();
		user.setUid(uid);
		user.setToken(token);
		return userDao.updateUser(user);
	}
	
}
