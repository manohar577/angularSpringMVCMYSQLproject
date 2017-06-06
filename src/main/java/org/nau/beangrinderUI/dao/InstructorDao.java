package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.nau.beangrinderUI.model.Instructor;
import org.nau.beangrinderUI.model.Student;


public class InstructorDao {
	Database database;
	 Connection connection;
	
	 public InstructorDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public Instructor getInstructorDetails(String id, String pass) throws Exception
	{
		Instructor instructor = new Instructor();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM instructor where Instructor_id = ? and Password = ?");
			ps.setString(1,id);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				instructor.setInstructorId(rs.getString("Instructor_id"));
				instructor.setPassword(rs.getString("Password"));
				instructor.setEmail(rs.getString("Instructor_Email"));
				instructor.setInstructorName(rs.getString("Instructor_name"));
				instructor.setDepartment(rs.getString("Instructor_dept"));
			}
			return instructor;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	public ArrayList<Instructor> getAllInstructorDetails() throws Exception
	{
		
		ArrayList<Instructor> instructorList = new ArrayList<Instructor>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM instructor");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Instructor instructor = new Instructor();
				instructor.setInstructorId(rs.getString("Instructor_id"));
				instructor.setEmail(rs.getString("Instructor_Email"));
				instructor.setInstructorName(rs.getString("Instructor_name"));
				instructor.setDepartment(rs.getString("Instructor_dept"));
				
				instructorList.add(instructor);
			}
			return instructorList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}	
	
	
	

	public String addInstructorAdmin(Instructor instructor) throws Exception
	{
		
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("INSERT INTO  instructor (Instructor_id, Instructor_name, Instructor_dept, Instructor_Email, Password) VALUES (?, ?, ?, ?, ?)");

			
			ps.setString(1,instructor.getInstructorId());
			ps.setString(2,instructor.getInstructorName());
			ps.setString(3,instructor.getDepartment());
			ps.setString(4, instructor.getEmail());
			ps.setString(5, instructor.getPassword());
			
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
	
	
	public String deleteInstructorAdmin(String id) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("DELETE FROM instructor WHERE instructor_id = ?");
			
			ps.setString(1,id);
			
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
	
	public String updateInstructorAdmin(String instructorId, Instructor instructor) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("UPDATE instructor SET Instructor_id = ?, "
							+ "Instructor_name= ? , Instructor_dept = ?, "
							+ "Instructor_Email = ? , password= ?  WHERE Instructor_id = ?");
			
			
			ps.setString(1,instructor.getInstructorId());
			ps.setString(2,instructor.getInstructorName());
			ps.setString(3,instructor.getDepartment());
			ps.setString(4, instructor.getEmail());
			ps.setString(5, instructor.getPassword());
			ps.setString(6, instructorId);
			
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
