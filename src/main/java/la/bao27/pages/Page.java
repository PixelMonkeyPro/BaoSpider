package la.bao27.pages;

import la.bao27.util.ElementUtil;

import java.util.stream.IntStream;

/**
 * @author Simon
 * @date 2019/7/3 9:36
 */

public class Page extends Urls {
	private Urls<String> item;

	public Page(Urls item) {
		this.item = item;
	}

	@Override
	public Page getCurrentResult() {
		item.parallelStream().forEach(url -> {
			ElementUtil elementUtil = new ElementUtil(url);
			int bound = Integer.parseInt(elementUtil.getText("a.a1").replaceAll("\\D", ""));
			synchronized (this) {
				add(url);
				IntStream.rangeClosed(2, bound).forEach(i -> add(url.replace(".html", "_" + i + ".html")));
			}
		});
		toFile();
		return this;
	}
}
