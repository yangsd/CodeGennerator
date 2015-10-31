package org.gennerator.constant;

/**
 * 项目的包名常量
 * 
 * @author sdyang
 * @date 2015年10月29日 下午4:52:16
 */
public class Project {

	/**
	 * 模块名称
	 */
	public static String moduleName = "sms";

	/**
	 * 实体包名
	 */
	public static String package_vo = "com." + Project.moduleName + ".model";

	/**
	 * DAO包名
	 */
	public static String package_dao = "com." + Project.moduleName + ".dao";

	/**
	 * 接口服务包名
	 */
	public static String package_service = "com." + Project.moduleName
			+ ".service";;

	/**
	 * 接口实现包名
	 */
	public static String package_serviceImpl = "com." + Project.moduleName
			+ ".service.impl";;

	/**
	 * 控制层包名
	 */
	public static String package_controller = "com." + Project.moduleName
			+ ".controller";;

}
