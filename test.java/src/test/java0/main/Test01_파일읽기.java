package test.java0.main;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test01_파일읽기 {
	public static void main(String[] args) throws Exception {
		
		// 이 File의 소스를 읽는 class. 실행 시키면 (주석 포함) 소스가 나옴. 
		// File IO
		// FileReader -> BufferedReader
		
		BufferedReader br = new BufferedReader(new FileReader("src/test/java0/main/Test01_파일읽기.java"));
		while(true) {
			String readLine = br.readLine();
			if(readLine == null) {
				break;
			}
			System.out.println(readLine);
		}
		br.close();
		
	}
}
