package org.nau.beangrinderUI.model;


public class Instructor {
	
	

 private String instructorId;
 private String instructorName;
 private String department;
 private String password;
 private String email;

	
	
 public String getInstructorId() {
		return instructorId;
	}





	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}





	public String getInstructorName() {
		return instructorName;
	}





	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
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
  return "Task [studentId=" + instructorId + ", studentName=" + instructorName
    + ", department=" + department + ", email="
    + email + "]";
 }
}
