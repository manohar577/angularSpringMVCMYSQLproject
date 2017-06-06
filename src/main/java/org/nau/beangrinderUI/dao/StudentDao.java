package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.nau.beangrinderUI.model.Course;
import org.nau.beangrinderUI.model.Student;
import org.nau.beangrinderUI.model.Task;


public class StudentDao {
	Database database;
	 Connection connection;
	
	 public StudentDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public Student getStudentDetails(String id, String pass) throws Exception
	{
		Student student = new Student();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM student where student_id = ? and password = ?");
			ps.setString(1,id);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				student.setStudentId(rs.getString("student_id"));
				student.setPassword(rs.getString("password"));
				student.setEmail(rs.getString("email"));
				student.setStudentName(rs.getString("student_name"));
				student.setDepartment(rs.getString("student_dept"));
			}
			return student;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	
	public ArrayList<Student> getAllStudentDetails() throws Exception
	{
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM student");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setEmail(rs.getString("email"));
				student.setStudentName(rs.getString("student_name"));
				student.setDepartment(rs.getString("student_dept"));
				
				studentList.add(student);
			}
			return studentList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}	
	
	
	public String addStudent(Student student) throws Exception
	{
		
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("INSERT INTO  student (student_id, student_name, student_dept, password, email) VALUES (?, ?, ?, ?, ?)");
			
			
			ps.setString(1,student.getStudentId());
			ps.setString(2,student.getStudentName());
			ps.setString(3,student.getDepartment());
			ps.setString(4, student.getPassword());
			ps.setString(5, student.getEmail());
			
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
	
	
	public String deleteStudentAdmin(String id) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("DELETE FROM student WHERE student_id = ?");
			
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
	
	public String updateStudentAdmin(String studentId, Student student) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("UPDATE student SET student_id = ?, "
							+ "student_name= ? , student_dept = ?, "
							+ "password= ?, email = ? WHERE student_id = ?");
			
			
			ps.setString(1,student.getStudentId());
			ps.setString(2,student.getStudentName());
			ps.setString(3,student.getDepartment());
			ps.setString(4, student.getPassword());
			ps.setString(5, student.getEmail());
			ps.setString(6, studentId);
			
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
