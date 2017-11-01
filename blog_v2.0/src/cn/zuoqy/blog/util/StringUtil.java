package cn.zuoqy.blog.util;

import java.util.UUID;

/**
 * @author zuoqiyin
 * @date 2017-9-19
 * @Description: 
 *     字符串工具
 * @version:1.0
 */
public class StringUtil {

	/**
	 * 返回UUID
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		str = str.replaceAll("-","");
		str = str.toUpperCase();
		return str;
	}
}
