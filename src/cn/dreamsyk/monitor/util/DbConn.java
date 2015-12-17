package cn.dreamsyk.monitor.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DbConn {

	/**
	 * 数据库连接操作类
	 * @author SYK	
	 * @time 2014-11-14 13:01:57
	 */
	private static String username = null;
	private static String password = null;
	private static String driver = null;
	private static String url = null;

	static{
		Properties ps=new Properties();
		try {
			ps.load(DbConn.class.getResourceAsStream("/db.properties"));
			driver=ps.getProperty("driver");
			url=ps.getProperty("url");
			username=ps.getProperty("username");
			password=ps.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		Connection con = null;
		try {
		    Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static void closeCon(Connection con) {
		
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		try {
			Connection conn = DbConn.getConn();
			if (conn != null) {
				System.out.println("数据库连接成功...");
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
