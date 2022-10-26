package kr.or.ddit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SampleDAOTest {
	SampleDAO dao = new SampleDAO();

	@Test
	public void testSelectData() {
		String result = dao.selectData();
		assertEquals("DATA", result);
	}

}
