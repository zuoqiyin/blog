package cn.zuoqy.blog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuoqy.blog.dao.MemberDao;
import cn.zuoqy.blog.model.Member;
import cn.zuoqy.blog.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 登陆
	 */
	@Override
	public Member login(String email, String pwd) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("pwd", pwd);
		return memberDao.getMember(params); 
	}

	@Override
	public int addMember(Member member) throws Exception {
		return memberDao.addMember(member);
	}

	@Override
	public Member getMemberByEmail(String email) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		return memberDao.getMember(params); 
	}

	@Override
	public Member getMemberByName(String name) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		return memberDao.getMember(params); 
	}

	@Override
	public int updateMember(Map<String, String> params) throws Exception {
		return memberDao.updateMember(params);
	}
	
}
