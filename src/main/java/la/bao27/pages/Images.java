package la.bao27.pages;

import com.alibaba.fastjson.JSON;
import la.bao27.interfaces.JSONIO;
import lombok.extern.java.Log;
import lombok.val;

import java.util.ArrayList;
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
			for (int i = 0; i < images.size(); i++) {
				this.add(JSON.parseObject(String.valueOf(images.get(i)), Image.class));
			}
			log.info("get previous directory");
		}
	}

	public void get() {
		val size = this.size();
		page.forEach(url -> {
			val id = index.incrementAndGet();

			Image image;
			if (id <= size) {
				image = this.get(id - 1);
				if (image.getStatus() != Status.AGAIN) {
					return;
				}
				image.setStatus(image.download());
			} else {
				image = new Image(url, id);
				add(image);
			}
			toFile();
			log.info(image.toString());

			try {
				Thread.sleep(new Random().nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	public void summary() {
		val total = page.size();
		val success = this.stream().filter(i -> i.getStatus() == Status.SUCCESS).count();
		val fail = this.stream().filter(i -> i.getStatus() == Status.FAIL).count();
		val again = this.stream().filter(i -> i.getStatus() == Status.AGAIN).count();

		System.out.println("total: " + total);
		System.out.println("success: " + success);
		System.out.println("fail: " + fail);
		System.out.println("again: " + again);
	}

}