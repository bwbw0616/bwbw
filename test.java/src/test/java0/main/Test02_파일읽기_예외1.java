package test.java0.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test02_파일읽기_예외1 {
	public static void main(String[] args) {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/test/java0/main/Test02_파일읽기_예외1.java"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			String readLine = null;
			try {
				readLine = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(readLine == null) {
				break;
			}
			System.out.println(readLine);
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
