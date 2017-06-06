package org.nau.beangrinderUI.model;


public class Activity {
 private String activityId;
 private String courseId;
 private String activityName;
 private String activityDetails;
 private String startDate;
 private String EndDate;

 



public String getCourseId() {
	return courseId;
}





public String getActivityId() {
	return activityId;
}





public void setActivityId(String activityId) {
	this.activityId = activityId;
}





public String getActivityDetails() {
	return activityDetails;
}





public void setActivityDetails(String activityDetails) {
	this.activityDetails = activityDetails;
}





public void setCourseId(String courseId) {
	this.courseId = courseId;
}





public String getActivityName() {
	return activityName;
}





public void setActivityName(String activityName) {
	this.activityName = activityName;
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










@Override
 public String toString() {
  return "Task [activityId=" + activityId + ",courseId=" + courseId + ", courseId=" + courseId + ", "
  		+ "activityDetails=" + activityDetails + ", startDate=" + startDate + ", EndDate="+ EndDate + "]";
 }
}
