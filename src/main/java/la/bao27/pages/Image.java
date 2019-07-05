package la.bao27.pages;

import la.bao27.config.Download;
import la.bao27.util.ElementUtil;
import la.bao27.util.Request;
import la.bao27.util.StringUtil;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.val;

import java.io.File;

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
	private Status status;
	private int id;

	public Image(@NonNull String page, int id) {
		setPage(page);
		setId(id);
		val image = new ElementUtil(page, "img").getFirstGifImage();
		setUrl(image.attr("src").trim());
		setName(getId() + "-" + StringUtil.filter(image.attr("alt")));
		setStatus(download());
	}

	@SneakyThrows
	public Status download() {
		val url = getUrl();
		if (url.startsWith("http://img.1000le.com")) {
			return Status.FAIL;
		}
		val name = getName() + ".gif";
		val file = new File(Download.getDirectory(), name);
		if (file.exists()) {
			return Status.SUCCESS;
		}

		val httpRequest = Request.get(url);
		if (!httpRequest.ok()) {
			return Status.AGAIN;
		}
		val contentLength = httpRequest.contentLength();
		if (contentLength > 8000 && contentLength < 9000) {
			return Status.FAIL;
		}
		file.createNewFile();
		httpRequest.receive(file);
		log.info(name + " has download.");
		return Status.SUCCESS;
	}

	@Override
	public String toString() {
		return getName() + " : " + getUrl() + System.lineSeparator()
			+ "status: " + getStatus() + System.lineSeparator();
	}

}
