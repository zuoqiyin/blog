package cn.zuoqy.blog.model;

/**
 * @author zuoqiyin
 * @date 2017-10-23
 * @Description: 评论 
 * @version:1.0
 */
public class Comment {
	
	private String cid; 	// 主键
	private String mid;		// 关联用户
	private String bid;		// 关联文章
	private String content; // 内容
	private String time;    // 时间
	private String dzan;    // 点赞次数
	
	private String mImg;	// 用户图片
	private String mName;	// 用户名称
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
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
	public String getmImg() {
		return mImg;
	}
	public void setmImg(String mImg) {
		this.mImg = mImg;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}

}
