package la.bao27.pages;

import la.bao27.config.Path;
import la.bao27.interfaces.JSONIO;
import lombok.extern.java.Log;
import lombok.val;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Simon
 * @project spider
 * @package la.bao27
 * @date 2019/7/4 12:20
 */

@Log
public class Images extends ArrayList<Image> implements JSONIO {
	private Urls<String> page;
	public static AtomicInteger index = new AtomicInteger();

	public Images(Urls page) {
		this.page = page;
		ArrayList<Image> images = toObject(Image.class);
		if (images != null) {
			this.addAll(images);
			log.finer("get previous image");
			index.set(this.size());
		}
	}

	public void get() {
		page.forEach(url -> {
			Optional<Image> optional = this.stream().filter(image -> image.getPage().equals(url)).findFirst();
			if (optional.isPresent() && optional.get().isOk()) {
				return;
			}
			val image = new Image(url);
			log.info(image.toString());
			add(image);
			toFile();
			try {
				Thread.sleep(new Random().nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		summary();
	}

	private void summary() {
		val total = page.size();
		log.info("total: " + total);
		val success = this.stream().filter(Image::isOk).count();
		log.info("success: " + success);
		val fail = total - success;
		log.info("fail: " + fail);
	}

	@Override
	public String getFilePath() {
		return Path.getImages();
	}
}