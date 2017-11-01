package cn.zuoqy.blog.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zuoqy.blog.model.User;

public interface UserService {

	public User getUserByUid(String uid) throws Exception;
	
	public User getUserByEmail(String email) throws Exception;
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addUser(User user) throws Exception;
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateUser(User user) throws Exception;
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateToken(String uid, String token) throws Exception;
}
