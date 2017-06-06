package org.nau.beangrinderUI.model;


public class Student {
 private String studentId;
 private String studentName;
 private String department;
 private String password;
 private String email;

 
 
 public String getStudentId() {
	return studentId;
}



public void setStudentId(String studentId) {
	this.studentId = studentId;
}



public String getStudentName() {
	return studentName;
}



public void setStudentName(String studentName) {
	this.studentName = studentName;
}



public String getDepartment() {
	return department;
}



public void setDepartment(String department) {
	this.department = department;
}



public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}



public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}



@Override
 public String toString() {
  return "Task [studentId=" + studentId + ", studentName=" + studentName
    + ", department=" + department + ", email="
    + email + "]";
 }
}
