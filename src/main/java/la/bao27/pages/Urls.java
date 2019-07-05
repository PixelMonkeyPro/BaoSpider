package la.bao27.pages;

import la.bao27.interfaces.Result;
import lombok.val;

import java.util.ArrayList;

/**
 * @author Simon
 * @project spider
 * @package la.bao27.pages
 * @date 2019/7/4 20:54
 */
public abstract class Urls<T> extends ArrayList<T> implements Result {

	public static Urls from(ArrayList urls) {
		val u = new Urls() {
			@Override
			public Urls getCurrentResult() {
				return null;
			}

			@Override
			public String getFilePath() {
				return null;
			}
		};
		u.addAll(urls);
		return u;
	}
}
