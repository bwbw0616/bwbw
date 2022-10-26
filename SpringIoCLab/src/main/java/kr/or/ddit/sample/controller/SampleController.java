package kr.or.ddit.sample.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SampleController {
	@Inject
	ApplicationContext context;
	
	@PostConstruct	//*** 모든 의존성 주입이 끝난 이후에 동작.
	public void init() {
		log.info("{} 객체 초기화", this.getClass());
		log.info("컨테이너 hashcode : {}", context.hashCode());
	}
	
	private SampleService service;
	
	@Inject
	public void setService(SampleService service) {
		this.service = service;
		log.info("주입된 객체 : {}, proxy 여부 : {}", service.getClass(), AopUtils.isAopProxy(service));
	}
	
	public void commandHandler(Map<String, Object> model, String code) {
		StringBuffer info = service.retriveSampleData(code);
		model.put("info", info);
	}
}
