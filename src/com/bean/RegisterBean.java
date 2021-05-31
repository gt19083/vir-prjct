package com.bean;
 
public class RegisterBean {
 private int id;
 private static String fullName;
 private int mobileno;
 private String userName;
 private String password;
 
 
public RegisterBean(int id, String name, int mobile, String password) {
	// TODO Auto-generated constructor stub
	this.id=id;
	this.userName=name;
	this.mobileno=mobile;
	this.password=password;
}
public RegisterBean() {
	// TODO Auto-generated constructor stub
}
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
 public void setFullName(String fullName) {
 this.fullName = fullName;
 }
 public String getFullName() {
 return fullName;
 }
 public void setMobileNo(int mobileno) {
 this.mobileno = mobileno;
 }
 public int getMobileNo() {
 return mobileno;
 }
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}