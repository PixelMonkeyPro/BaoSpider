package la.bao27.util;

import java.util.regex.Pattern;

/**
 * @author Simon
 * @project bao27
 * @package la.bao27.util
 * @date 2019/7/5 12:15
 */
public class StringUtil {
	public static String filter (String str){
		return Pattern.compile("[`\"~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]").matcher(str).replaceAll("").trim();
	}
}
