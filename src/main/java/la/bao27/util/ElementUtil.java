package la.bao27.util;

import lombok.Data;
import lombok.val;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Simon
 * @date 2019/7/2 17:01
 */
@Data
public class ElementUtil {
	private Elements elements;
	private Document document;

	public ElementUtil(String url) {
		this.document = Jsoup.parse(Request.get(url).body());
	}

	public ElementUtil(String url, String selector) {
		this.document = Jsoup.parse(Request.get(url).body());
		this.elements = document.select(selector);
	}
	public Element getFirstGifImage() {
		val images = getElements();
		return images.stream()
			.filter(s -> s.attr("src").trim().endsWith("gif"))
			.findFirst().get();
	}
	public String getText() {
		return elements.first().text();
	}

	public String getAttr(String attr) {
		return elements.first().attr(attr);
	}

	public ArrayList<String> getAttrs(String attr) {
		return (ArrayList<String>) elements.parallelStream().map(element -> element.attr(attr)).distinct().collect(Collectors.toList());
	}

	public String getText(String selector) {
		elements = document.select(selector);
		return elements.first().text();
	}

	public String getAttr(String selector, String attr) {
		elements = document.select(selector);
		return elements.first().attr(attr);
	}

	public ArrayList<String> getAttrs(String selector, String attr) {
		elements = document.select(selector);
		return (ArrayList<String>) elements.parallelStream().map(element -> element.attr(attr)).distinct().collect(Collectors.toList());
	}
}
