/**
 *
 */
package com.internousdev.JissenKadai4.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author internous
 *mysqlへ接続する、conオブジェクトを返すためのメソッドを提供するクラス
 */
public class DBConnector {

	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/openconnect";

	//適宜各自の環境に応じた設定にしてください（特にいじっていないのであればこのままでOK）
	private static String user = "root";
	private static String password = "mysql";

	private static Connection con = null;

	public static Connection getConnection() {
		con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return con;
	}

}
