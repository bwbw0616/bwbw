package kr.or.ddit.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 인코딩(Encoding)
 * ?? 데이터를 전송하거나 저장하기 위해서 매체가 인지할 수 있는 방식으로 데이터의 표현 방법을 바꾸는 작업.
 * ex) URLEncoding(network), Base64(범용 인코딩)
 *
 */
public class EncodingDesc {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String original = "ABC123한글";
		
		String urlEncoded = URLEncoder.encode(original, "utf-8");
		System.out.println(urlEncoded);
	}
}
