package la.bao27.util;

import com.github.kevinsawicki.http.HttpRequest;
import la.bao27.config.Header;
import la.bao27.config.RandomUserAgent;
import lombok.NonNull;

/**
 * @author Simon
 * @date 2019/7/3 16:13
 */
public class Request {

	public static HttpRequest get(@NonNull String url) {
		HttpRequest httpRequest = HttpRequest.get(url);
		httpRequest.userAgent(RandomUserAgent.getRandomUserAgent());
//		httpRequest.referer(RandomRefer.getRandomRefer());
		httpRequest.header("Cookie", Header.getCookie());
		httpRequest.header("X-Forwarded-For", Header.getXForwardedFor());
//		httpRequest.useProxy(Header.getProxyAddress(), Header.getProxyPort());
		httpRequest.connectTimeout(Integer.MAX_VALUE);
		return httpRequest;
	}
}
