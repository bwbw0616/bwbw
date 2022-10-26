package kr.or.ddit.sample;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.collection.properties.dbInfoVO;
import kr.or.ddit.sample.controller.SampleController;
import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.view.SampleView;

public class ApplicationEntryPoint {
	public static void main(String[] args) {
		ConfigurableApplicationContext parentContainer =
				new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/Parent-Context.xml");
		ConfigurableApplicationContext childContainer1 = 
				new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/sample/conf/child-context.xml"}, parentContainer);
		ConfigurableApplicationContext childContainer2 = 
				new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/collection/properties/conf/Properties-Context.xml"}, parentContainer);
		childContainer1.registerShutdownHook();
		childContainer2.registerShutdownHook();
		parentContainer.registerShutdownHook();
		
		SampleController controller = childContainer1.getBean(SampleController.class);
		SampleView view = childContainer1.getBean(SampleView.class);
		String code = "a001";
		Map<String, Object> model = new LinkedHashMap<>();
		controller.commandHandler(model, code);
		view.rendering(model);
		
		SampleService service = childContainer1.getBean(SampleService.class);
		System.err.println(service);
		
		dbInfoVO infoVO1 = parentContainer.getBean("infoVO1", dbInfoVO.class);
		System.err.println(infoVO1.getService());
		
		childContainer1.getBean("infoVO1", dbInfoVO.class);
	}
}
