package org.nau.beangrinderUI.model;


public class Admin {
	
	






private String adminId;
private String adminName;
private String email;
private String password;

 public String getAdminId() {
		return adminId;
	}




 public String getPassword() {
	return password;
}








public void setPassword(String password) {
	this.password = password;
}



	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}








	public String getAdminName() {
		return adminName;
	}








	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}








	public String getEmail() {
		return email;
	}








	public void setEmail(String email) {
		this.email = email;
	}




	

 
 
 

@Override
 public String toString() {
  return "Task [adminId=" + adminId + ", adminName=" + adminName+ ", email="
    + email + "]";
 }
}
