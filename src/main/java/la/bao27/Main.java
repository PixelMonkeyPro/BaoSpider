package la.bao27;

import la.bao27.pages.*;
import lombok.extern.java.Log;

/**
 * @author Simon
 * @date 2019/7/2 15:42
 */
@Log
public class Main {
	public static void main(String[] args) {
		log.info("start...");
		Urls list = new List().getResult();
		log.info("finish list");
		Urls item = new Item(list).getResult();
		log.info("finish item");
		Urls page = new Page(item).getResult();
		log.info("finish page");
		new Images(page).get();
		log.info("finish all");
	}
}