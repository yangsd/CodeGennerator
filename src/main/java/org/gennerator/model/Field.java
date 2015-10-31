package org.gennerator.model;

/**
 * 字段
 * 
 * @author sdyang
 * @date 2015年10月28日 下午3:12:12
 */
public class Field {

	/**
	 * 数据库列名
	 */
	private String column;

	/**
	 * 字段类型
	 */
	private String type;

	/**
	 * 字段备注
	 */
	private String comment;

	/**
	 * 首字母大写
	 */
	private String property;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
