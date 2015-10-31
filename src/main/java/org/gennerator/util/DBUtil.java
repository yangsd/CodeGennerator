package org.gennerator.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 
 * 
 * @author sdyang
 * @date 2015年10月29日 上午9:13:56
 */
public class DBUtil {

	private static DataSource ds = null;

	static {
		try {
			Resource conf = new ClassPathResource("database.properties");
			Properties props = new Properties();
			props.load(conf.getInputStream());
			ds = DruidDataSourceFactory.createDataSource(props);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}