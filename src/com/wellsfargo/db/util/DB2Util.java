package com.wellsfargo.db.util;

import static log.Logger.stdout;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DB2Util {
	
	private static final String DB2DRIVER; 
	private static final String USERNAME; 
	private static final String PASSWORD; 
	private static final String URL; 
	private static final Properties sql = new Properties();
	
	
	static {
 		Properties p = new Properties();
 		
 		
 		String env = System.getProperty("env");
 		String filename = env.equalsIgnoreCase("dev") ? System.getProperty("devfile"): System.getProperty("prodfile");
 		String sqlFilename = System.getProperty("sqlQueries");
 		stdout(filename);
 		
		InputStream is = DB2InsertUtil.class.getResourceAsStream(filename);
		InputStream sqlStream = DB2InsertUtil.class.getResourceAsStream(sqlFilename);
		try {
			p.load(is);
			sql.load(sqlStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				is.close();
				sqlStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		DB2DRIVER = p.get("faidb2.driver").toString();
		PASSWORD = p.get("faidb2.password").toString();
		USERNAME = p.get("faidb2.username").toString();
		URL = p.get("faidb2.url").toString();
//		stdout(DB2DRIVER);stdout(PASSWORD);stdout(USERNAME);stdout(URL);
//		stdout(sql.getProperty("SNFSELECT"));
	}
	
	
	public static void main(String[] args) {
		Connection db2Con = null;
		PreparedStatement preparedStmt = null;
//		PreparedStatement delPreparedStmt = null;
//		Statement stmt = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName(DB2DRIVER);
			db2Con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stdout("Connection to the DB is " + db2Con);
						
			preparedStmt = db2Con.prepareStatement(sql.getProperty("SNFALLSELECT"));
			//preparedStmt.setInt(1, 1);
			resultSet = preparedStmt.executeQuery();
			stdout(resultSet);
			if (resultSet != null) {
				int cols = resultSet.getMetaData().getColumnCount();
				stdout("Column count " + cols + " Column name: " + resultSet.getMetaData().getColumnName(1));
				while (resultSet.next()) {
					for(int i = 1;i <= cols;i++) {
						stdout(resultSet.getMetaData().getColumnName(1) + ": " + resultSet.getString(1));
						if (i ==1) {
							i++;i++;
						}
					}
					stdout("\n");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
