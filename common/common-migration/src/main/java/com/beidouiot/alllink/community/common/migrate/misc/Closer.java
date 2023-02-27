package com.beidouiot.alllink.community.common.migrate.misc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Closer {

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception ignored) {
			}
		}
	}
	
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception ignored) {
			}
		}
	}
	
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception ignored) {
			}
		}
	}
	
}
