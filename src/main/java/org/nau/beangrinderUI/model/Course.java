package org.nau.beangrinderUI.model;


public class Course {
 private String courseId;
 private String courseName;
 private String startDate;
 private String EndDate;
 private String instructorId;

 



public String getCourseId() {
	return courseId;
}





public void setCourseId(String courseId) {
	this.courseId = courseId;
}





public String getCourseName() {
	return courseName;
}





public void setCourseName(String courseName) {
	this.courseName = courseName;
}





public String getStartDate() {
	return startDate;
}





public void setStartDate(String startDate) {
	this.startDate = startDate;
}





public String getEndDate() {
	return EndDate;
}





public void setEndDate(String endDate) {
	EndDate = endDate;
}





public String getInstructorId() {
	return instructorId;
}





public void setInstructorId(String instructorId) {
	this.instructorId = instructorId;
}





@Override
 public String toString() {
  return "Task [courseId=" + courseId + ", courseName=" + courseName
    + ", startDate=" + startDate + ", EndDate="
    + EndDate + ", instructorId="+ instructorId +"]";
 }
}
