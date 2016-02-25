package com.internousdev.JissenKadai4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.JissenKadai4.dto.LoginDTO;
import com.internousdev.JissenKadai4.util.DBConnector;


/*
 * SQLをメソッドにラップして使うためのクラス（おそらく）
 */
public class LoginDAO {

	private static String admin_name;

	public boolean selectUserInfo(String user, String password) {
		Connection conn = null;
		LoginDTO dto = new LoginDTO();
		boolean ret = false;
		try {
			conn = (Connection) DBConnector.getConnection();
			//sqlを実行して、その結果ひとつでもレコードが取れたらretにsuccessが入り、認証されたと設定する。
			String sql = "select * from admin_table where username = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//dtoへ値をセット
				dto.setUsername(rs.getString("username"));
				dto.setPassword(rs.getString("password"));
//				System.out.println(admin_name);
				ret = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	//静的フィールドを複数の利用先クラスでインスタンス化して参照するために、getsetでラップ（カプセル化）する
	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		LoginDAO.admin_name = admin_name;
	}

}
