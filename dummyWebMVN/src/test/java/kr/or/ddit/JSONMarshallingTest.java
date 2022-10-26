package kr.or.ddit;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMarshallingTest {
	static ObjectMapper mapper;
	@BeforeClass
	public static void setUpClass(){
		mapper = new ObjectMapper();
	}
	
	Map<String, Object> target = new HashMap<>();
	@Before
	public void setUp() {
		target.put("prop1", "TEXT_VALUE");
		target.put("prop2", 23);
		target.put("prop3", false);
		target.put("prop4", new String[] {"a", "b"});
	}
	
	@Test
	public void testMarshalling(){
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, target);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testUnmarshalling() throws StreamReadException, DatabindException, IOException {
		InputStream is = JSONMarshallingTest.class.getResourceAsStream("sample.json");
		// 역직렬화->unmarshalling
		Map<String, Object> result = mapper.readValue(is, HashMap.class);
		System.out.println(result);
	}
}










