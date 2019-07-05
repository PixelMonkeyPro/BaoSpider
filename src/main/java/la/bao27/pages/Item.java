package la.bao27.pages;

import la.bao27.util.ElementUtil;

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
			ElementUtil elementUtil = new ElementUtil(url, "body > section > ul > li > a");
			ArrayList<String> href = elementUtil.getAttrs("href");
			synchronized (this) {
				this.addAll(href);
			}
		});
		toFile();
		return this;
	}
}
