package cn.zuoqy.blog.model;

/**
 * @author zuoqiyin
 * @date 2017-9-22
 * @Description: verify验证码
 * @version:1.0
 */
public class Verify {

	private String vid;
	private String question;//问题
	private String answer;//答案
	
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
