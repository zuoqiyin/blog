package cn.zuoqy.blog.service;

import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zuoqy.blog.model.Member;

/**
 * @author zuoqiyin
 * @date 2017-9-22
 * @Description: memberService
 * @version:1.0
 */
public interface MemberService {

	public Member login(String email,String pwd) throws Exception;
	
	public Member getMemberByEmail(String email) throws Exception;
	public Member getMemberByName(String name) throws Exception;
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addMember(Member member) throws Exception;
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateMember(Map<String, String> params) throws Exception;
}
