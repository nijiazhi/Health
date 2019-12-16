package iscas.otcaix.di.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

public class DataSourceFactory {
	private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

	public static Connection getConnection() {
		return getConnection(null);
	}

	public static Connection getConnection(String dataSourceName) {
		if (dataSourceName == null)
			dataSourceName = "dataSource_basic";
		if (!dataSourceMap.containsKey(dataSourceName)) {
			cacheDataSource(dataSourceName);
		}
		try {
			return dataSourceMap.get(dataSourceName).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void cacheDataSource(String dataSourceName) {
		if (!dataSourceMap.containsKey(dataSourceName)) {
			ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			DataSource dataSource = ctx.getBean(dataSourceName, DataSource.class);
			dataSourceMap.put(dataSourceName, dataSource);
//			System.out.println("缓存数据源:" + dataSourceName);
		}
	}
}
