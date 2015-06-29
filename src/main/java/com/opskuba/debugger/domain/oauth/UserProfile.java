package com.opskuba.debugger.domain.oauth;

public class UserProfile {
	
	private Long id;
	
	private String nickName;
	private String password;
	private String realName;
	
	private String loginAddress;
	
	public UserProfile() {
		
	}

	public UserProfile(String nickName, String password) {
		super();
		this.nickName = nickName;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginAddress() {
		return loginAddress;
	}

	public void setLoginAddress(String loginAddress) {
		this.loginAddress = loginAddress;
	}
	
}
