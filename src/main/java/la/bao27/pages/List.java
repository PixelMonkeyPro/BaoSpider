package la.bao27.pages;

import la.bao27.util.ElementUtil;
import lombok.extern.java.Log;
import lombok.val;

import java.util.stream.IntStream;

/**
 * @author Simon
 * @date 2019/7/3 18:35
 */
@Log
public class List extends Urls {

	private static String joinUrl(int num) {
		return "https://m.27bao.la/gif/list_" + num + ".html";
	}

	private static int getBound() {
		val element = new ElementUtil(joinUrl(1),"body > section > center > div > a:last-child");
		return Integer.valueOf(element.getAttr("href").replaceAll("\\D", ""));
	}

	@Override
	public List getCurrentResult() {
		IntStream.rangeClosed(1, getBound()).forEach(i -> add(joinUrl(i)));
		toFile();
		return this;
	}
}
