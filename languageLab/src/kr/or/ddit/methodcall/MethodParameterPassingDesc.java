package kr.or.ddit.methodcall;

/**
 * 메소드 호출 시 인자(argument) 전달 방법
 * parameter vs argument
 * 	 ex) int number = 3; Object obj = new Object();
 *  call by value : parameter 타입이 기본형.(copy value)
 *  call by reference : parameter 타입이 참조형. (copy reference)
 *
 */
public class MethodParameterPassingDesc {
	public static void main(String[] args) {
		int number = 10;
		method1(number);
		System.out.printf("number : %d\n", number);
		
		StringBuffer sb = new StringBuffer("ORIGINAL");
		method2(sb);
		System.out.printf("sb : %s", sb);
	}
	
	private static void method1(int number) {
		number = number + 1;
	}
	
	private static void method2(StringBuffer sb) {
		sb.append(" APPEND ");
	}
}
