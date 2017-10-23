package cn.zuoqy.blog.dao;

import java.util.Map;

import cn.zuoqy.blog.model.Member;

/**
 * @author zuoqiyin
 * @date 2017-9-22
 * @Description: memberDao
 * @version:1.0
 */
public interface MemberDao {

	public Member getMember(Map<String, String> params) throws Exception;
	
	public int addMember(Member member) throws Exception;
	
	public int updateMember(Map<String, String> params) throws Exception;
}
