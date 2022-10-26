package kr.or.ddit.javaconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.stereotype.ViewLayer;

@Configuration
@Lazy
@ComponentScan(basePackages="kr.or.ddit.sample", useDefaultFilters=false
, includeFilters= {
		@Filter(Component.class)
}, excludeFilters= {
		@Filter(classes=Controller.class)
		, @Filter(classes=ViewLayer.class)
})
public class JavaConfigForContainer {
//	@Bean
////	@Scope("prototype")
//	@Lazy
//	public SampleDAO sampleDAOMaria() {
//		return new SampleDAO_Maria();
//	}
//	
//	@Bean("sampleService")
//	public SampleService sampleService(SampleDAO dao) {
//		return new SampleServiceImpl(dao);
//	}
}
