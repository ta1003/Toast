package com.happy.toast.dtos;

import java.io.Serializable;

public class ToastUser_DTO implements Serializable {

	
	private static final long serialVersionUID = 768729796133533136L;
	
	private String userid;
	private String password;
	private String nickname;
	private String address; 
	private String phone;
	private String email;
	private String auth;
	private String regdate;

	public ToastUser_DTO() {
	}

	
	public ToastUser_DTO(String userid, String password, String nickname, String email, String auth, String regdate) {
		super();
		this.userid = userid;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.auth = auth;
		this.regdate = regdate;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAuth() {
		return auth;
	}


	public void setAuth(String auth) {
		this.auth = auth;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	@Override
	public String toString() {
		return "Toastuser_DTO [userid=" + userid + ", password=" + password + ", nickname=" + nickname + ", address="
				+ address + ", phone=" + phone + ", email=" + email + ", auth=" + auth + ", regdate=" + regdate + "]";
	}

	
	
	
}
