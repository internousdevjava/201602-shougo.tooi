/**
 *
 */
package com.internousdev.JissenKadai4.dto;

/**
 * @author internous
 *利用先からインスタンスを作り、情報を共用するためにカプセル化するクラス
 */
public class LoginDTO {
	private static String username;
	private static String password;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		LoginDTO.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		LoginDTO.username = username;
	}
}
