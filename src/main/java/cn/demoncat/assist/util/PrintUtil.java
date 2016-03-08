package cn.demoncat.assist.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

/**
 * 便捷的输出打印：输出Object和集合、响应json
 */
public class PrintUtil {

	/**
	 * 方法的描述 : 用于在测试时便捷的输出 Object、Map、List、Set、Array 到控制台
	 */
	public static void sop(Map<?, ?> map){
		Set<?> s = map.keySet();
		for(Object k : s){
			Object v = map.get(k);
			System.out.println(k+":"+v);
		}
	}
	public static void sop(Set<?> set){
		for(Object o : set){
			System.out.println(o);
		}
	}
	public static void sop(List<?> list){
		for(Object o : list){
			System.out.println(o);
		}
	}
	public static void sop(Object o){
		System.out.println(o);
	}
	
	public static void sop(Object[] arr){
		for(Object o : arr){
			System.out.println(o);
		}
	}

	/**
	 * 通过Response向客户端发送Json等字符串信息
	 */
	public static void printJson(String json,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}	
	}
}
