package cn.demoncat.assist.util;

public class StringUtil {


	/**
	 * 判断String是否不为null或""。
	 */
	public static boolean isNotNull(String str){
		if(str == null){
			return false;
		}
		if("".equals(str.trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * null字符串转换为""，非null字符串去除两端空格
	 */
	public static String parseStr(String str){
		if(null == str){
			return "";
		}else{
			return str.trim();
		}
	}
	
	/**
	 * 单词首字母转换为大写true/小写false
	 */
	public static String firstUpperOrLower(String str, boolean upper){
		if(upper){
			str = str.substring(0,1).toUpperCase() + str.substring(1);
		}
		if(!upper){
			str = str.substring(0,1).toLowerCase() + str.substring(1);
		}
		return str;
	}
}
