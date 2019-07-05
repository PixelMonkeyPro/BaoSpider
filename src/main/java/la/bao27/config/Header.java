package la.bao27.config;

import lombok.Getter;

/**
 * @author Simon
 * @project spider
 * @package la.bao27.config
 * @date 2019/7/3 20:01
 */
public class Header {
	@Getter
	public static final String cookie = "coopv1720167520190703=5; UM_distinctid=16bb6c47a0d279-0a46466d23afda-27116e5e-4e7c2-16bb6c47a0e358; CNZZDATA1277656550=1476578528-1562737416-%7C5562137416; CNZZDATA1271543934=2027872454-1562937047-https%253A%252F%252Fm.27bao.la%252F%7C1562187067; is_show_dsn=1; UBGLAI63GV=yHDtr.1862179482; iiad_img_has_show_1790=45%7C18%2C48%7C16; _s_v_129=18%2R";
	@Getter
	public static final String proxyAddress = "localhost";
	@Getter
	public static final int proxyPort = 1080;
	@Getter
	public static final String XForwardedFor = "220.181.38.148";
}
