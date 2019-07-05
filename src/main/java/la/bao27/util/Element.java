package la.bao27.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Simon
 * @date 2019/7/2 17:01
 */
public class Element {
	private Elements elements;
	private Document document;

	public Element(String url) {
		this.document = Jsoup.parse(Request.get(url).body());
	}

	public Element(String url, String selector) {
		this.document = Jsoup.parse(Request.get(url).body());
		this.elements = document.select(selector);
	}

	public String getLastAttr(String attr) {
		return elements.last().attr(attr);
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
