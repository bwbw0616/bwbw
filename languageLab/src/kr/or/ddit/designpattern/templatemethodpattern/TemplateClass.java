package kr.or.ddit.designpattern.templatemethodpattern;

/**
 * Template method pattern
 *  : 특정 작업의 단계가 명확히 고정되어있고, 해당 단계 내의 세부 작업의 내용이 여러 형태가 존재할 때 사용하는 구조 패턴.
 *  
 *  1. template class : 작업의 단계를 정의하고 있는 클래스를 재정의하지 않음.
 *  	1) template method : 작업의 단계 정의
 *  	2) hook method(abstract) : 단계 내의 세부 작업 정의
 *  2. concrete class : template 내에 정의된 단계에서 어느 한 세부 단계의 작업을 정의하고 있는 클래스.
 *
 */
public abstract class TemplateClass {
	
	public final void template() {
		stepOne();
		stepTwo();
		stepThreed();
	}
	
	private void stepOne() {
		System.out.println("1단계");
	}
	
	protected abstract void stepTwo();
	
	private void stepThreed() {
		System.out.println("3단계");
	}
}
