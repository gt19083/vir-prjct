package com.bean;
 
public class LoginBean
{
     private String userName;
     private String password;
     private int userid;

    public String getUserName() {
        return userName;
     }
    public void setUserName(String userName) {
        this.userName = userName;
     }
     public String getPassword() {
        return password;
     }
     public void setPassword(String password) {
        this.password = password;
     }
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
}