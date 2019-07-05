package la.bao27.config;

import java.util.Random;

/**
 * @author Simon
 * @date 2019/7/3 15:44
 */
public class RandomRefer {
	private static final String[] refers = {"https://ip.rtbasia.com/", "https://ip.rtbasia.com/", "https://www.baidu.com/",
		"https://www.google.com/", "https://www.hao123.com/", "http://www.sogou.com"};
	public static String getRandomRefer() {
		return refers[new Random().nextInt(refers.length)];
	}
}
