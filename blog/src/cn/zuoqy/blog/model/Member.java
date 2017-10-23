package cn.zuoqy.blog.model;

import java.util.Date;

/**
 * @author zuoqiyin
 * @date 2017-9-22
 * @Description: 用户
 * @version:1.0
 */
public class Member {
	
	private String mid;
	private String email;   //邮箱
	private String name;    //昵称
	private String sex;     //性别
	private String city;    //城市
	private String sign;    //签名
	private String img;     //图片
	private String pwd;     //密码
	private String qq;
	private String weibo;
	private Date updateTime; //修改日期
	private Date createTime; //创建时间
	
	private String isActivate; //是否激活 Y-N
	private String token;	   // token码
	
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIsActivate() {
		return isActivate;
	}
	public void setIsActivate(String isActivate) {
		this.isActivate = isActivate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
