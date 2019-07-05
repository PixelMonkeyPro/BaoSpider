package la.bao27.pages;

import la.bao27.config.Path;
import la.bao27.util.Element;

import java.util.ArrayList;

/**
 * @author Simon
 * @date 2019/7/3 8:50
 */

public class Item extends Urls {
	private Urls<String> list;

	public Item(Urls list) {
		this.list =  list;
	}

	@Override
	public Item getCurrentResult() {
		list.parallelStream().forEach(url -> {
			Element element = new Element(url, "body > section > ul > li > a");
			ArrayList<String> href = element.getAttrs("href");
			synchronized (this) {
				this.addAll(href);
			}
		});
		toFile();
		return this;
	}

	@Override
	public String getFilePath() {
		return Path.getItem();
	}
}
