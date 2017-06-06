package org.nau.beangrinderUI.model;


public class StudentCourse {
 private String courseId;
 private String studentId;
 private String courseName;
 private String studentName;
 private String studentEmail;

 

 public String getCourseId() {
		return courseId;
	}


	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentId() {
		return studentId;
	}


	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	 public String getStudentName() {
			return studentName;
		}


		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}


		public String getStudentEmail() {
			return studentEmail;
		}


		public void setStudentEmail(String studentEmail) {
			this.studentEmail = studentEmail;
		}



@Override
 public String toString() {
  return "Task [courseId=" + courseId + ", courseName=" + courseName+", "
  		+ "studentName=" + studentName+",studentEmail=" + studentEmail+", "+"studentId="+ studentId +"]";
 }
}
