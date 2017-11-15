package cn.zuoqy.blog.model;

import java.util.List;

/**
 * @author zuoqiyin
 * @date 2017-11-2
 * @Description: message留言与评论
 * @version:1.0
 */
public class Message {

	private String mid;
	private String uid;       // 关联用户
	private String aid;		  // 关联文章
	private String content;   // 内容
	private String time;	  // 时间
	private String dzan;	  // 点赞次数
	private String isYe;	  // 是否是叶子节点,若是,填父节点id,不是为null
	
	private String name;
	private String img;
	
	private List<Message> item; // 子集
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDzan() {
		return dzan;
	}
	public void setDzan(String dzan) {
		this.dzan = dzan;
	}
	public String getIsYe() {
		return isYe;
	}
	public void setIsYe(String isYe) {
		this.isYe = isYe;
	}
	public List<Message> getItem() {
		return item;
	}
	public void setItem(List<Message> item) {
		this.item = item;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
