package cn.zuoqy.blog.model;

/**
 * @author zuoqiyin
 * @date 2017-9-20
 * @Description: 文章类
 * @version:1.0
 */
public class Bean {

	private String id;
	private String title;
	private String msg;
	private String time;
	private String anthor;
	private String type;
	private String img;//展示图片路径
	private String commentCount;//评论次数
	private String viewCount;//浏览次数
	private String msgSmall;//内容小概括
	
	//冗余字段
	private String typeName;//类型名称
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAnthor() {
		return anthor;
	}
	public void setAnthor(String anthor) {
		this.anthor = anthor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	public String getViewCount() {
		return viewCount;
	}
	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	public String getMsgSmall() {
		return msgSmall;
	}
	public void setMsgSmall(String msgSmall) {
		this.msgSmall = msgSmall;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
