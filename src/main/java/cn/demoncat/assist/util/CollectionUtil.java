package cn.demoncat.assist.util;

import java.util.Collection;

public class CollectionUtil {
	
	/**
	 * 判断Collection是否非空：
	 * 如果Collection为null或empty，返回false
	 */
	public static boolean isNotNull(Collection<?> c){
		if(c == null || c.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 添加非空元素：
	 * 如果Collection为空返回false；如果elemnt为null返回false；如果都不为空则添加并返回true
	 */
	public static <E> boolean addNotNullElement(Collection<E> c, E e){
		if(! isNotNull(c)){
			return false;
		}
		if(e == null){
			return false;
		}
		c.add(e);
		return true;
	}
	
	/**
	 * 添加非空元素集:
	 * 如果Collection为空返回false；如果elemnts为空返回false；如果都不为空则添加并返回true
	 */
	public static <E> boolean addNotNullElements(Collection<E> c, Collection<E> es){
		if(! isNotNull(c)){
			return false;
		}
		if(! isNotNull(es)){
			return false;
		}
		c.addAll(es);
		return true;
	}
}
