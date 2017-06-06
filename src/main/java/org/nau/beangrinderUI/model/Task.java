package org.nau.beangrinderUI.model;


public class Task {
	
	private String taskId;
	 private String activityId;
	 private String taskName;
	 private String taskStatement;
	 private String taskSolution;
	 private String startDate;
	 private String endDate;

	 
	@Override
	 public String toString() {
	  return "Task [taskId=" + taskId + ",activityId=" + activityId + ", taskName=" + taskName + ", "+
			  "taskSolution=" + taskSolution + ","
	  		+ "taskStatement=" + taskStatement + ", startDate=" + startDate + ", EndDate="+ endDate + "]";
	 }
	
	
	
	
	
 public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public String getActivityId() {
		return activityId;
	}


	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getTaskStatement() {
		return taskStatement;
	}


	public void setTaskStatement(String taskStatement) {
		this.taskStatement = taskStatement;
	}


	public String getTaskSolution() {
		return taskSolution;
	}


	public void setTaskSolution(String taskSolution) {
		this.taskSolution = taskSolution;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



}
