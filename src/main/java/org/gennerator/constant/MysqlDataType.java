package org.gennerator.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * mysql数据类型
 * 
 * @author sdyang
 * @date 2015年10月28日 上午9:44:34
 */
public class MysqlDataType {

	public static String CHAR = "char";

	public static String DATE = "date";

	public static String TIMESTAMP = "timestamp";

	public static String INT = "int";

	public static String BIGINT = "bigint";

	public static String TEXT = "text";

	public static String BIT = "bit";

	public static String DECIMAL = "decimal";

	public static String BLOB = "blob";
	
	public static String DOUBLE = "double";

	public static Map<String, String> DataType = new HashMap<String, String>() {
		{
			put(MysqlDataType.CHAR, "String");
			put(MysqlDataType.BIGINT, "Long");
			put(MysqlDataType.INT, "Integer");
			put(MysqlDataType.DATE, "Date");
			put(MysqlDataType.TIMESTAMP, "Date");
			put(MysqlDataType.DECIMAL, "java.math.BigDecimal");
			put(MysqlDataType.BLOB, "byte[]");
			put(MysqlDataType.BIT, "Boolean");
			put(MysqlDataType.DOUBLE,"double");
		}
	};
}
