package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Properties props = new Properties();
	private static Connection cnn = null;

	public static Connection getConnection() {
		if (cnn == null) {
			try {
				props = loadProperties();
				String url = props.getProperty("dburl");
				cnn = DriverManager.getConnection(url, props);
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}

		return cnn;
	}

	public static void closeConnection(Connection cnn) {
		if (cnn != null) {
			try {
				cnn.close();
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (Exception ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}
