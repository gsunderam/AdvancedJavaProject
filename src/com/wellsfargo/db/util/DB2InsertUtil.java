package com.wellsfargo.db.util;

import static log.Logger.stdout;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.util.Collections;
import java.util.Properties;

public class DB2InsertUtil {
	
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
		PreparedStatement delPreparedStmt = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName(DB2DRIVER);
			db2Con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stdout("Connection to the DB is " + db2Con);
						
			preparedStmt = db2Con.prepareStatement(sql.getProperty("SNFSELECT"));
			preparedStmt.setInt(1, 1);
//			preparedStmt.setString(2, "2");
			
			Thread selectThread = new Thread(new SelectThread(resultSet, preparedStmt));
			selectThread.run();
			
			delPreparedStmt = db2Con.prepareStatement(sql.getProperty("SNFDELETE"));
			delPreparedStmt.setString(2, "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (preparedStmt != null) preparedStmt.close();
				if (stmt != null) stmt.close();
				if (db2Con != null) db2Con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	}
	
	static class SelectThread implements Runnable {
		private ResultSet resultSet;
		private PreparedStatement preparedStmt;
		
		public SelectThread(ResultSet resultSet, PreparedStatement prepStatement) {
			this.resultSet = resultSet;
			this.preparedStmt = prepStatement;
		}
		
		public void run() {
			while (true) {
				try {
					resultSet = preparedStmt.executeQuery();
					stdout(resultSet);
					if (resultSet != null) {
						int cols = resultSet.getMetaData().getColumnCount();
						stdout("Column count " + cols + " Column name: " + resultSet.getMetaData().getColumnName(1));
						while (resultSet.next()) {
							for(int i = 1;i <= cols;i++) {
								stdout(resultSet.getMetaData().getColumnName(5) + ": " + resultSet.getString(5));
								if (i ==1) {
									i++;i++;
								}
							}
							stdout("\n");
						}
						Thread.sleep(10000);
					}
				} catch(SQLException sqle) {
					sqle.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class DeleteThread implements Runnable {
		private ResultSet resultSet;
		private PreparedStatement preparedStmt;
		
		public DeleteThread(ResultSet resultSet, PreparedStatement prepStatement) {
			this.resultSet = resultSet;
			this.preparedStmt = prepStatement;
		}
		public void run() {
			while (true) {
				
			}
		}
	}
	

}
