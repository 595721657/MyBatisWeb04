package com.entity;
/**
 * 用户实体
 * @author 黄龙
 * @@创建时间 2020年4月10日上午10:15:54
 */
public class User {
       private String userId;
       private String userPsw;
       private String userName;
       private int role;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPsw() {
		return userPsw;
	}
	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public User(String userId, String userPsw, String userName, int role) {
		super();
		this.userId = userId;
		this.userPsw = userPsw;
		this.userName = userName;
		this.role = role;
	}
	public User() {
		super();
	}
       
}
