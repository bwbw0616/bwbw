package kr.or.ddit.designpattern.templatemethodpattern;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemplateClassTest {

	@Test
	public void testTemplate() {
		TemplateClass derived1 = new DerivedClass1();
		TemplateClass derived2 = new DerivedClass2();
		TemplateClass[] array = new TemplateClass[] {derived1, derived1};
		for(TemplateClass single : array) {
			single.template();
		}
	}

}
