package cn.demoncat.assist.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {

	/**
	 * 判断类型是否为基本业型（String|Long|Integer|Date|Double|BigDecimal）
	 */
	public static boolean isBaseType(String fieldType) {
		if (fieldType.matches("(String|Long|Integer|Date|Double|BigDecimal)")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断类型是否为集合类型（List、Set、Map）
	 */
	public static boolean isCollectionType(String fieldType) {
		if (fieldType.matches(".*(List|Set|Map)")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断类型是否为POJO类型（非基本类型或集合类型）
	 */
	public static boolean isPojoType(String fieldType) {
		if (isCollectionType(fieldType) || isBaseType(fieldType)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 将指定的属性名，转换为相应的get方法名
	 */
	public static String fieldGetName(String fieldName) {
		fieldName = StringUtil.firstUpperOrLower(fieldName, true);
		return "get" + fieldName;
	}

	/**
	 * 将指定的属性名，转换为相应的set方法名
	 */
	public static String fieldSetName(String fieldName) {
		fieldName = StringUtil.firstUpperOrLower(fieldName, true);
		return "set" + fieldName;
	}

	/**
	 * 获取集合属性的元素类型
	 */
	public static Class<?> getListFieldElementType(Field listField) throws ClassNotFoundException {
		// 获取完整的字段类型(包含集合的泛型)
		String genericType = listField.getGenericType().toString();
		// 获取泛型的类型
		String substring = genericType.substring(genericType.indexOf("<") + 1, genericType.length() - 1);
		return Class.forName(substring);
	}

	/**
	 * 遍历类型的私有属性，获取Base和Date类型的属性(true表示包含id字段)
	 */
	public static List<Field> getBaseFields(Class<?> clazz, boolean containId)
			throws InstantiationException, IllegalAccessException {
		List<Field> fs = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if ("id".equals(field.getName())) {
				if (!containId) {
					continue;
				}
			}
			Class<?> type = field.getType();
			String fieldType = type.getSimpleName();
			if (isBaseType(fieldType)) {
				fs.add(field);
			}
		}
		return fs;
	}

	/**
	 * 遍历类型的私有属性，获取POJO类型的属性
	 */
	public static List<Field> getPojoFields(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		List<Field> fs = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Class<?> type = field.getType();
			String fieldType = type.getSimpleName();
			if (isPojoType(fieldType)) {
				fs.add(field);
			}
		}
		return fs;
	}

	/**
	 * 遍历类型的私有属性，获取List类型的属性
	 */
	public static List<Field> getListFields(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		List<Field> fs = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Class<?> type = field.getType();
			String fieldType = type.getSimpleName();
			if (isCollectionType(fieldType)) {
				fs.add(field);
			}
		}
		return fs;
	}
}
