package util;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

import exceptions.ExceptionSql;

public class ConnectionFactory {

	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	public static String ipServidor = "localhost";
	
	// ----------------------------------------------------------------------------
	public Connection getConnectionLocal() {

		String url = "jdbc:mysql://localhost:3306/araujo_seguros_local";
		//String url = "jdbc:mysql://localhost:3306/araujo_seguros";

		try {
			Class.forName(driver);
			return (Connection) DriverManager.getConnection(url, user, "root");
			//return (Connection) DriverManager.getConnection(url, user, "AraujoCaneta");
		} catch (Exception e) {
			//e.printStackTrace();
			new ExceptionSql();
		}
		return null;
	}

	// ---------------------------------------------------------------------------
	public Connection getConnectionIntranet() {

		String url = "jdbc:mysql://"+ipServidor+":3306/araujo_seguros?connectTimeout=500";

		//String url = "jdbc:mysql://localhost:3306/araujo_seguros";
		try {
			Class.forName(driver);
			//return (Connection) DriverManager.getConnection(url, "root", "araujotecksoft");
			return (Connection) DriverManager.getConnection(url, "root", "root");
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	// -------------------------------------------------------------------------bgb--
	
	
}