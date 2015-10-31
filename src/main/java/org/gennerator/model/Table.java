package org.gennerator.model;

/**
 * 数据库表
 * 
 * @author sdyang
 * @date 2015年10月28日 下午3:23:19
 */
public class Table {

	/**
	 * 主键
	 */
	private String primary_key;

	/**
	 * 数据表名称
	 */
	private String name;

	/**
	 * 表备注信息
	 */
	private String comment;

	/**
	 * 实体名称
	 */
	private String beanName;

	/**
	 * Mapper名称
	 */
	private String mapperName;

	/**
	 * 首字母大写
	 */
	private String aliasName;

	/**
	 * DAO名称
	 */
	private String daoName;

	/**
	 * 接口服务名称
	 */
	private String serviceName;

	/**
	 * 实现类名称
	 */
	private String serviceImplName;

	/**
	 * Controller名称
	 */
	private String controllerName;

	public String getPrimary_key() {
		return primary_key;
	}

	public void setPrimary_key(String primary_key) {
		this.primary_key = primary_key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMapperName() {
		return mapperName;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceImplName() {
		return serviceImplName;
	}

	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

}
