package test.java0.main;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test03_파일읽기_예외2 {
	public static void main(String[] args) throws Exception {
		
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
