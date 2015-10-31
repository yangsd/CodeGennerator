package org.gennerator.constant;


/**
 * 文件路径常量
 * 
 * @author sdyang
 * @date 2015年10月29日 下午5:18:35
 */
public class FilePath {

	private static String PATH = "E:\\" + Project.moduleName.toUpperCase()
			+ "\\com" + "\\" + Project.moduleName + "\\";

	/**
	 * 实体类文件路径
	 */
	public static String vo_path = FilePath.PATH + "model";

	/**
	 * DAO类文件路径
	 */
	public static String dao_path = FilePath.PATH + "dao";

	/**
	 * Mapper类文件路径
	 */
	public static String mapper_path = FilePath.PATH + "mapper";

	/**
	 * 接口服务类文件路径
	 */
	public static String service_path = FilePath.PATH + "service";

	/**
	 * 接口服务实现类文件路径
	 */
	public static String serviceImpl_path = FilePath.PATH + "service\\impl";

	/**
	 * Controller类文件路径
	 */
	public static String controller_path = FilePath.PATH + "controller";

}
