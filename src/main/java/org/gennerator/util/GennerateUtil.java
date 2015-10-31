package org.gennerator.util;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gennerator.constant.FilePath;
import org.gennerator.constant.MysqlDataType;
import org.gennerator.constant.Project;
import org.gennerator.model.Field;
import org.gennerator.model.Table;

/**
 * 创建mybatis的Mapper文件、DAO文件、Service文件、ServiceImpl文件、Controller文件
 * 
 * @author sdyang
 * @date 2015年10月19日 下午4:52:45
 */
public class GennerateUtil {

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @author sdyang
	 * @date 2015年10月29日 下午5:59:14
	 */
	private static List<Table> getTables() throws SQLException {
		List<Table> tables = new ArrayList<Table>();

		Map<String, String> primaryKeyMap = getPrimaryKey(Project.moduleName);

		PreparedStatement pstate = DBUtil.getConnection().prepareStatement(
				"show table status");
		ResultSet results = pstate.executeQuery();
		while (results.next()) {
			Table table = new Table();
			table.setName(results.getString("NAME"));
			table.setComment(results.getString("COMMENT"));
			table.setAliasName(table.getName().substring(0, 1).toUpperCase()
					+ table.getName().substring(1));
			table.setBeanName(table.getAliasName() + "VO");
			table.setMapperName(table.getAliasName() + "Mapper");
			table.setDaoName(table.getAliasName() + "DAO");
			table.setServiceName("I" + table.getAliasName() + "Service");
			table.setServiceImplName(table.getAliasName() + "ServiceImpl");
			table.setControllerName(table.getAliasName() + "Controller");
			table.setPrimary_key(primaryKeyMap.get(table.getName()));
			tables.add(table);
		}
		return tables;
	}

	/**
	 * 
	 * @param databaseName
	 * @return
	 * @throws SQLException
	 * @author sdyang
	 * @date 2015年10月29日 下午5:59:18
	 */
	private static Map<String, String> getPrimaryKey(String databaseName)
			throws SQLException {
		Map<String, String> result = new HashMap<String, String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" t.table_name, ");
		sql.append(" t.constraint_type, ");
		sql.append(" c.column_name, ");
		sql.append(" c.ordinal_position ");
		sql.append(" from ");
		sql.append(" information_schema.table_constraints as t, ");
		sql.append("  information_schema.key_column_usage as c ");
		sql.append(" where ");
		sql.append(" t.table_name = c.table_name ");
		sql.append(" and t.table_schema = '" + databaseName + "' ");
		sql.append(" and t.constraint_type = 'primary key'; ");

		PreparedStatement pstate = DBUtil.getConnection().prepareStatement(
				sql.toString());
		ResultSet results = pstate.executeQuery();
		while (results.next()) {
			result.put(results.getString("table_name"),
					results.getString("column_name"));
		}
		return result;
	}

	/**
	 * 
	 * @param fields
	 * @param table
	 * @throws IOException
	 * @author sdyang
	 * @date 2015年10月29日 下午5:59:21
	 */
	private static void buildEntityBean(List<Field> fields, Table table)
			throws IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("package", Project.package_vo);
		parameters.put("table", table);
		parameters.put("fields", fields);
		parameters.put("date", DateTimeUtils.getCurrentDate());

		String result = TemplateUtil.getInstance().getText("bean", parameters);
		FileUtil.createFile(FilePath.vo_path + "\\" + table.getBeanName()
				+ ".java", result);
	}

	/**
	 * 
	 * @param field
	 * @return
	 * @author sdyang
	 * @date 2015年10月29日 下午5:59:32
	 */
	private static String processField(String field) {
		return field.substring(0, 1).toUpperCase() + field.substring(1);
	}

	/**
	 * 
	 * @param type
	 * @return
	 * @author sdyang
	 * @date 2015年10月29日 下午5:59:36
	 */
	private static String processType(String type) {
		for (String key : MysqlDataType.DataType.keySet()) {
			if (type.indexOf(key) > -1) {
				return MysqlDataType.DataType.get(key);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param fields
	 * @param table
	 * @throws IOException
	 * @author sdyang
	 * @date 2015年10月29日 下午5:59:39
	 */
	private static void buildMapperXml(List<Field> fields, Table table)
			throws IOException {

		if (table.getPrimary_key() == null || table.getPrimary_key().equals("")) {
			table.setPrimary_key(fields.get(0).toString());
		}
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("namespace",
				Project.package_dao + "." + table.getDaoName());
		parameters.put("table", table);
		parameters.put("fields", fields);
		parameters.put("date", DateTimeUtils.getCurrentDate());

		String result = TemplateUtil.getInstance()
				.getText("mapper", parameters);

		FileUtil.createFile(FilePath.mapper_path + "\\" + table.getMapperName()
				+ ".xml", result);
	}

	/**
	 * 
	 * @param fields
	 * @param table
	 * @throws IOException
	 * @author sdyang
	 * @date 2015年10月29日 下午5:59:43
	 */
	private static void buildDao(List<Field> fields, Table table)
			throws IOException {

		if (table.getPrimary_key() == null || table.getPrimary_key().equals("")) {
			table.setPrimary_key(fields.get(0).toString());
		}

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("package", Project.package_dao);
		parameters.put("voclass",
				Project.package_vo + "." + table.getBeanName());
		parameters.put("table", table);
		parameters.put("fields", fields);
		parameters.put("date", DateTimeUtils.getCurrentDate());

		String result = TemplateUtil.getInstance().getText("dao", parameters);
		FileUtil.createFile(FilePath.dao_path + "\\" + table.getDaoName()
				+ ".java", result);
	}

	/**
	 * 
	 * @param fields
	 * @param table
	 * @throws IOException
	 * @author sdyang
	 * @date 2015年10月29日 下午6:00:15
	 */
	private static void buildService(List<Field> fields, Table table)
			throws IOException {

		if (table.getPrimary_key() == null || table.getPrimary_key().equals("")) {
			table.setPrimary_key(fields.get(0).toString());
		}

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("package", Project.package_service);
		parameters.put("voclass",
				Project.package_vo + "." + table.getBeanName());
		parameters.put("table", table);
		parameters.put("fields", fields);
		parameters.put("date", DateTimeUtils.getCurrentDate());

		String result = TemplateUtil.getInstance().getText("service",
				parameters);
		FileUtil.createFile(
				FilePath.service_path + "\\" + table.getServiceName() + ".java",
				result);
	}

	/**
	 * 
	 * @param fields
	 * @param table
	 * @throws IOException
	 * @author sdyang
	 * @date 2015年10月29日 下午6:00:11
	 */
	private static void buildServiceImpl(List<Field> fields, Table table)
			throws IOException {

		if (table.getPrimary_key() == null || table.getPrimary_key().equals("")) {
			table.setPrimary_key(fields.get(0).toString());
		}

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("package", Project.package_serviceImpl);
		parameters.put("voclass",
				Project.package_vo + "." + table.getBeanName());
		parameters.put("daoclass",
				Project.package_dao + "." + table.getDaoName());
		parameters.put("serviceclass",
				Project.package_service + "." + table.getServiceName());
		parameters.put("table", table);
		parameters.put("fields", fields);
		parameters.put("date", DateTimeUtils.getCurrentDate());

		String result = TemplateUtil.getInstance().getText("serviceImpl",
				parameters);
		FileUtil.createFile(
				FilePath.serviceImpl_path + "\\" + table.getServiceImplName()
						+ ".java", result);
	}

	/**
	 * 
	 * @param fields
	 * @param table
	 * @throws IOException
	 * @author sdyang
	 * @date 2015年10月29日 下午6:00:06
	 */
	private static void buildController(List<Field> fields, Table table)
			throws IOException {

		if (table.getPrimary_key() == null || table.getPrimary_key().equals("")) {
			table.setPrimary_key(fields.get(0).toString());
		}
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("package", Project.package_controller);
		parameters.put("voclass",
				Project.package_vo + "." + table.getBeanName());
		parameters.put("daoclass",
				Project.package_dao + "." + table.getDaoName());
		parameters.put("serviceclass",
				Project.package_service + "." + table.getServiceName());
		parameters.put("table", table);
		parameters.put("fields", fields);
		parameters.put("date", DateTimeUtils.getCurrentDate());

		String result = TemplateUtil.getInstance().getText("controller",
				parameters);
		FileUtil.createFile(
				FilePath.controller_path + "\\" + table.getControllerName()
						+ ".java", result);
	}

	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @author sdyang
	 * @date 2015年10月29日 下午5:59:26
	 */
	public static void generate() throws ClassNotFoundException, SQLException,
			IOException {
		String prefix = "show full fields from ";
		List<Field> fields = null;
		PreparedStatement pstate = null;
		List<Table> tables = getTables();
		for (Table table : tables) {
			fields = new ArrayList<Field>();
			pstate = DBUtil.getConnection().prepareStatement(
					prefix + table.getName());
			ResultSet results = pstate.executeQuery();
			while (results.next()) {
				Field field = new Field();
				field.setColumn(results.getString("FIELD"));
				field.setType(processType(results.getString("TYPE")));
				field.setComment(results.getString("COMMENT"));
				field.setProperty(processField(results.getString("FIELD")));
				fields.add(field);
			}
			buildEntityBean(fields, table);
			buildMapperXml(fields, table);
			buildDao(fields, table);
			buildService(fields, table);
			buildServiceImpl(fields, table);
			buildController(fields, table);
			System.out.println("成功创建表【" + table.getName() + "】的java文件");
		}
	}

}
