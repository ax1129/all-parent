package cn.demoncat.assist.converter;

import org.springframework.core.convert.converter.Converter;

/** 										
 * 字符串类型转换器(绑定字符串类型时，先将null转换为""，再剪去非空字符串的空格)					 
 */
public class StringConverter implements Converter<String, String> {

	public String convert(String source) {
		
		try {
			if(null == source){
				return "";
			}else{
				return source.trim();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return source;
	}

}
