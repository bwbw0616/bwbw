package kr.or.ddit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLMarshallingTest {
	static ObjectMapper mapper;
	@BeforeClass
	public static void setUpClass() {
		mapper = new XmlMapper();
	}
	Map<String, Object> target = new HashMap<>();
	@Before
	public void setUp() {
		target.put("prop1", "TEXT_VALUE");
		target.put("prop2", 23);
		target.put("prop3", false);
		target.put("prop4", new String[] {"a", "b"});
	}
	
//	@Test
	public void test() throws StreamWriteException, DatabindException, IOException {
		mapper.writerWithDefaultPrettyPrinter()
			  .withRootName("root")
			  .writeValue(System.out, target);
	}

	@Test
	public void testUnmarshalling() throws StreamReadException, DatabindException, IOException {
		InputStream is = XMLMarshallingTest.class.getResourceAsStream("sample.xml");
		Map<String, Object> result = mapper.readValue(is, HashMap.class);
		System.out.println(result);
	}
}










