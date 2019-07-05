package la.bao27.pages;

import la.bao27.config.Path;
import la.bao27.util.Element;
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
		val element = new Element(joinUrl(1),"body > section > center > div > a:last-child");
		return Integer.valueOf(element.getAttr("href").replaceAll("\\D", ""));
	}

	@Override
	public String getFilePath() {
		return Path.getList();
	}

	@Override
	public List getCurrentResult() {
		int bound = getBound();
		log.info("listBound: "+ bound);
		IntStream.rangeClosed(1, bound).forEach(i -> add(joinUrl(i)));
		toFile();
		log.info("list toFile finish");
		return this;
	}
}
