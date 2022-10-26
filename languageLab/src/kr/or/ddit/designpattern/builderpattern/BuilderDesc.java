package kr.or.ddit.designpattern.builderpattern;

/**
 * Design Pattern
 * 생성 패턴 : Singleton pattern, Factory [Method] pattern, Builder pattern
 * 구조 패턴 : Adapter(Wrapper) pattern, Facade pattern
 * 행위 패턴 : Template Method pattern, Stategy pattern
 *
 */
public class BuilderDesc {
	public static void main(String[] args) {
//		1. 생성자 사용.
//		SampleVO sample = new SampleVO("1번의 값", "2번의 값", "3번의 값");
//		2. JavaBean 구조 사용
//		SampleVO sample2 = new SampleVO();
//		sample2.setProp1("1번의 값");
//		sample2.setProp3("3번의 값");
//		3. Builder 패턴
		SampleVO sample3 = SampleVO.builder()
//							.prop1("1번의 값")
//							.prop3("3번의 값")
							.prop2("2번의 값")
							.build();
		System.out.println(sample3);
	}
}
