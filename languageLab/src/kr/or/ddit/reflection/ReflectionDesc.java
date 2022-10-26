package kr.or.ddit.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

import kr.or.ddit.reflect.ReflectionTest;

/**
 * Reflection??
 * 		: 객체로부터 객체의 구성 요소, 특징, 속성, 형태들을 찾아가는 과정. java.lang.reflection 패키지로 지원.
 *
 */
public class ReflectionDesc {
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		Class type = obj.getClass();
		System.out.println(type);
		Field[] fields = type.getDeclaredFields();
		for(Field fld : fields) {
			Class fldType = fld.getType();
			String fldName = fld.getName();
//			memId -> getMemId
			String getterName = "get" + fldName.substring(0, 1).toUpperCase()+fldName.substring(1);
			try {
				Method getter = type.getMethod(getterName);
				// member.getMemId();
				Object returnValue = getter.invoke(obj);
				System.out.printf("%s %s = %s;\n"
						, fldType.getSimpleName(), fldName, Objects.toString(returnValue));
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Method[] methods = type.getDeclaredMethods();
		for(Method mtd : methods) {
			String mtdName = mtd.getName();
			Parameter[] parameter = mtd.getParameters();
			Class mtdReturnType = mtd.getReturnType();
			System.out.printf(" %s %s(%s)\n"
									, mtdReturnType.getSimpleName()
									, mtdName
									, Arrays.toString(parameter)
					);
		}
	}
}
