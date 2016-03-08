package cn.demoncat.assist.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
 
/** 										
 * 日期类型转换器(绑定字符串类型的时间参数时，先转换为Date)					 
 */
public class DateConverter implements Converter<String,Date> {
	
	public Date convert(String value) {
		SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return s1.parse(value);
		} catch (Exception e) {
			try {
				return s2.parse(value);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
 
}