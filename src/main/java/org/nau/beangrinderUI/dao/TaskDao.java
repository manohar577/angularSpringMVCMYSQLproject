package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.nau.beangrinderUI.model.Activity;
import org.nau.beangrinderUI.model.Task;


public class TaskDao {
	
	Database database;
	 Connection connection;
	
	public TaskDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public ArrayList<Task> getTaskDetails(String activityId) throws Exception
	{
		ArrayList<Task> taskList = new ArrayList<Task>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM tasks where activity_id= ?");
			ps.setString(1,activityId);
			ResultSet rs = ps.executeQuery();
			
				
			while(rs.next())
			{
						Task task = new Task();
						task.setTaskId(rs.getString("task_id"));
						task.setActivityId(rs.getString("activity_id"));
						task.setTaskName(rs.getString("task_name"));
						task.setTaskStatement(rs.getString("task_statement"));
						task.setEndDate(rs.getString("task_end_date"));
						task.setStartDate(rs.getString("task_start_date"));
						
						taskList.add(task);
			}
			
			return taskList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	
	public String addTask(Task task) throws Exception
	{
		int returnValue = 0;
		try
		{
			
			PreparedStatement psAct = 
					connection.prepareStatement("SELECT max(task_id) as taskId FROM tasks;");
			ResultSet rs = psAct.executeQuery();
			String taskId = "";
			
			int taskNumber = 0;
			while(rs.next())
			 taskId = rs.getString("taskId");
			
			String[] x = taskId.split("T");
			
			if(x[1] != null )
				taskNumber = Integer.parseInt(x[1].trim()) + 1;
					
			taskId = "T"+taskNumber;
			
			PreparedStatement ps = 
					connection.prepareStatement("INSERT INTO  tasks ( task_id, task_Name, task_statement, task_solution, activity_id, task_start_date, task_end_date) VALUES ( ?, ?, ?, ?, ?, ?, ?)");
			

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			
			java.util.Date date = sdf1.parse(task.getStartDate());
			java.sql.Date startDate = new java.sql.Date(date.getTime());
			 date = sdf1.parse(task.getEndDate());
			java.sql.Date endDate = new java.sql.Date(date.getTime());  
			
			ps.setString(1, taskId);
			ps.setString(2,task.getTaskName());
			ps.setString(3,task.getTaskStatement());
			ps.setString(4,task.getTaskSolution());
			ps.setString(5, task.getActivityId());
			ps.setDate(6,startDate);
			ps.setDate(7, endDate);
			
			returnValue = ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			throw e;
		}
		if(returnValue != 0)
		return returnValue+"";
		else return null;
	}
	
	
	public String deleteTask(String taskId) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("DELETE FROM tasks WHERE task_id = ?");
			
			ps.setString(1,taskId);
			
			returnValue = ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			throw e;
		}
		if(returnValue != 0)
		return returnValue+"";
		else return null;
	}
	
}
