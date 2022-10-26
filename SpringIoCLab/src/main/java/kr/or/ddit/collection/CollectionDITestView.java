package kr.or.ddit.collection;

import java.util.Arrays;
import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollectionDITestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/collection/conf/CollectionDI-Context.xml");
		context.registerShutdownHook();
		
		CollectionDIVO colVO1 = context.getBean("colVO1", CollectionDIVO.class);
		log.info("CollectionDIVO-1 printing");
		printProperties(colVO1);
		CollectionDIVO colVO2 = context.getBean("colVO2", CollectionDIVO.class);
		log.info("CollectionDIVO-2 printing");
		printProperties(colVO2);
		
		
		Date now = context.getBean("now", Date.class);
		log.info("now : {}", now);
	}
	
	private static void printProperties(CollectionDIVO colVO) {
		Arrays.stream(colVO.getArray()).forEach((element)->{
			log.info("element value : {}", element);
		});
		colVO.getList().forEach((element)->{
			log.info("element value : {}", element);
		});
		colVO.getSet().forEach((element)->{
			log.info("element value : {}", element);
		});
		colVO.getMap().forEach((key, value)->{
			log.info("key : {}, value : {}", key, value);
		});
		colVO.getProps().forEach((key, value)->{
			log.info("key : {}, value : {}", key, value);
		});
	}
}





