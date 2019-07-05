package la.bao27.pages;

import la.bao27.config.Path;
import la.bao27.util.Element;
import la.bao27.util.Request;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.val;

import java.io.File;
import java.io.IOException;

/**
 * @author Simon
 * @project spider
 * @package la.bao27.pages
 * @date 2019/7/4 22:43
 */
@Data
@Log
public class Image {
	private String name;
	private String page;
	private String url;
	private boolean ok;

	public Image(@NonNull String page) {
		setPage(page);

		val image = new Element(page, "img");
		setName(Images.index.incrementAndGet() + "-" + image.getLastAttr("alt"));
		setUrl(image.getLastAttr("src"));
		setOk(checkAndDownloadWhenOK());
	}

	@SneakyThrows
	private boolean checkAndDownloadWhenOK() {
		String filePath = joinFilePath();
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		}
		val url = getUrl();
		if (url.startsWith("http://img.1000le.com")) {
			return false;
		}
		val httpRequest = Request.get(url);
		if (!httpRequest.ok()) {
			return false;
		}
		int contentLength = httpRequest.contentLength();
		if (contentLength > 1000 && contentLength < 10000) {
			return false;
		}
		if (!file.createNewFile()) {
			throw new IOException("file create fail.");
		}
		httpRequest.receive(file);
		log.info(filePath + " has download.");
		return true;
	}

	private String joinFilePath() {
		return Path.getImage() + this.name + ".gif";
	}

	@Override
	public String toString() {
		return getName() + " : " + getUrl() + System.lineSeparator()
			+ "status: " + isOk() + System.lineSeparator();
	}
}
