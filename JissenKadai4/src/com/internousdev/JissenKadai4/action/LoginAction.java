/**
 *
 */
package com.internousdev.JissenKadai4.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.JissenKadai4.dao.LoginDAO;
import com.internousdev.JissenKadai4.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author internous
 *
 */
public class LoginAction extends ActionSupport implements SessionAware {
	// login.jspの<s:~~~>のname属性で指定した名前とフィールド名を一致させることで、入力されてきたデータが受け取れる?
	private String id;
	private String password;
	private Map<String, Object> session ;
	public String execute() throws SQLException {
		LoginDAO dao = new LoginDAO();
		LoginDTO dto = new LoginDTO();
		String ret = "error";
		if (dao.selectUserInfo(id, password)) {
			// sql文によってはここで条件判定
			//本当は必要ないが、dtoの使い道を試す
			if (dto.getUsername().equals(id) && dto.getPassword().equals(password)) {
				session.put("user_name", id.toString());
				ret = "success";
			}
		}

		return ret;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO 自動生成されたメソッド・スタブ
		session = arg0;

	}


}