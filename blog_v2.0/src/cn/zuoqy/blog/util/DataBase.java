package cn.zuoqy.blog.util;

/**
 * @author zuoqiyin
 * @date 2017-9-22
 * @Description: 数据返回基本类型
 * @version:1.0
 */
public class DataBase {

	private String msg;
	private int code;
	private Object item;
	
	private int count;//数据总条数
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getItem() {
		return item;
	}
	public void setItem(Object item) {
		this.item = item;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
