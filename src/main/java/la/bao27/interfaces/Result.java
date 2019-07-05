package la.bao27.interfaces;

import la.bao27.pages.Urls;
import lombok.val;

import java.util.ArrayList;
import java.util.Optional;
public interface Result extends JSONIO{
	Urls getCurrentResult();
	default Urls getPreviousResult() {
		ArrayList urls = toObject(String.class);
		if (urls == null) {
			return null;
		}
		return Urls.from(urls);
	}
	default Urls getResult() {
		val className = this.getClass().getSimpleName();
		System.out.println(className + " is working...");
		Optional<Urls> optional = Optional.ofNullable(getPreviousResult());
		if (optional.isPresent()) {
			System.out.println(className+ ": use previous result");
			return optional.get();
		}
		System.out.println(className+ ": get current result");
		return getCurrentResult();
	}
}
