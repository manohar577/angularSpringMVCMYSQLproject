package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.nau.beangrinderUI.model.Activity;
import org.nau.beangrinderUI.model.Course;


public class ActivityDao {
	
	Database database;
	 Connection connection;
	
	public ActivityDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public ArrayList<Activity> getActivityDetails(String courseId) throws Exception
	{
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM activities where courseId= ?");
			ps.setString(1,courseId);
			ResultSet rs = ps.executeQuery();
			
				
			while(rs.next())
			{
						Activity activity = new Activity();
						activity.setCourseId(rs.getString("CourseId"));
						activity.setActivityId(rs.getString("Activity_Id"));
						activity.setActivityName(rs.getString("Activity_Name"));
						activity.setActivityDetails(rs.getString("Activity_Details"));
						activity.setEndDate(rs.getString("Activity_End_Date"));
						activity.setStartDate(rs.getString("Activity_Start_Date"));
						
						activityList.add(activity);
			}
			
			return activityList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	public String addActivity(Activity activity) throws Exception
	{
		int returnValue = 0;
		try
		{
			
			PreparedStatement psAct = 
					connection.prepareStatement("SELECT max(Activity_Id) as actID FROM activities;");
			ResultSet rs = psAct.executeQuery();
			String actId = "";
			
			int actNumber = 0;
			while(rs.next())
			 actId = rs.getString("actID");
			
			String[] x = actId.split("A");
			
			if(x[1] != null )
				  actNumber = Integer.parseInt(x[1].trim()) + 1;
					
			actId = "A"+actNumber;
			
			PreparedStatement ps = 
					connection.prepareStatement("INSERT INTO  activities ( Activity_Id, CourseId, Activity_Start_Date, Activity_End_Date, Activity_Name, Activity_Details) VALUES ( ?, ?, ?, ?, ?, ?)");
			
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			
			java.util.Date date = sdf1.parse(activity.getStartDate());
			java.sql.Date startDate = new java.sql.Date(date.getTime());
			 date = sdf1.parse(activity.getEndDate());
			java.sql.Date endDate = new java.sql.Date(date.getTime());  
			
			ps.setString(1, actId);
			ps.setString(2,activity.getCourseId());
			ps.setDate(3,startDate);
			ps.setDate(4, endDate);
			ps.setString(5, activity.getActivityName());
			ps.setString(6, activity.getActivityDetails());
			
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
	
	
	public String deleteActivity(String activityId) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("DELETE FROM activities WHERE Activity_Id = ?");
			
			ps.setString(1,activityId);
			
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
