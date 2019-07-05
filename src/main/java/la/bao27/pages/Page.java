package la.bao27.pages;

import la.bao27.config.Path;
import la.bao27.util.Element;

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

	public Page() {

	}

	@Override
	public Page getCurrentResult() {
		item.parallelStream().forEach(url -> {
			Element element = new Element(url);
			int bound = Integer.parseInt(element.getText("a.a1").replaceAll("\\D", ""));
			synchronized (this) {
				add(url);
				IntStream.rangeClosed(2, bound).forEach(i -> add(url.replace(".html", "_" + i + ".html")));
			}
		});
		toFile();
		return this;
	}

	@Override
	public String getFilePath() {
		return Path.getPage();
	}
}
