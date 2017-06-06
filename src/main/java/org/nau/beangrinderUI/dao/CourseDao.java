package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.nau.beangrinderUI.model.Course;
import org.nau.beangrinderUI.model.StudentCourse;


public class CourseDao {
	
	Database database;
	 Connection connection;
	
	public CourseDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public ArrayList<Course> getCourseDetails(String studentId) throws Exception
	{
		ArrayList<Course> courseList = new ArrayList<Course>();
		ArrayList<String> courseIdList = new ArrayList<String>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM course");
			PreparedStatement psCourse = 
					connection.prepareStatement("SELECT * FROM student_course where Student_Id = ?");
			psCourse.setString(1,studentId);
			ResultSet rs = ps.executeQuery();
			ResultSet rsCourse = psCourse.executeQuery();
			
			while(rsCourse.next())
				courseIdList.add(rsCourse.getString("Course_Id"));
				
			while(rs.next())
			{
				if(courseIdList.contains(rs.getString("Course_id"))) {
						Course course = new Course();
						course.setCourseId(rs.getString("Course_id"));
						course.setCourseName(rs.getString("Course_Name"));
						course.setStartDate(rs.getString("Course_Start_Date"));
						course.setEndDate(rs.getString("Course_End_Date"));
						course.setInstructorId(rs.getString("Instructor_Id"));
						
						courseList.add(course);
				}
				
			}
			
			return courseList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	
	public ArrayList<Course> getCourseDetailsByInstructor(String instructorId) throws Exception
	{
		ArrayList<Course> courseList = new ArrayList<Course>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM course where Instructor_Id = ?");
			ps.setString(1,instructorId);
			ResultSet rs = ps.executeQuery();
			
				
			while(rs.next())
			{
						Course course = new Course();
						course.setCourseId(rs.getString("Course_id"));
						course.setCourseName(rs.getString("Course_Name"));
						course.setStartDate(rs.getString("Course_Start_Date"));
						course.setEndDate(rs.getString("Course_End_Date"));
						course.setInstructorId(rs.getString("Instructor_Id"));
						
						courseList.add(course);
				
			}
			
			return courseList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	public ArrayList<StudentCourse> getStudentCourseDetailsByCourse(String courseId, String courseName) throws Exception
	{
		ArrayList<StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
		ArrayList<String> studentIdList = new ArrayList<String>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM student_course where Course_Id = ?");
			ps.setString(1,courseId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				studentIdList.add(rs.getString("Student_Id"));
			
			for(String studentId : studentIdList) {
			
			PreparedStatement psStudent = 
					connection.prepareStatement("SELECT * FROM student where student_id = ?");
			psStudent.setString(1,studentId);
			ResultSet rsStudent = psStudent.executeQuery(); 
				
			while(rsStudent.next())
			{
						StudentCourse studentCourse = new StudentCourse();
						
						studentCourse.setCourseId(courseId);
						studentCourse.setCourseName(courseName);
						studentCourse.setStudentId(rsStudent.getString("student_id"));
						studentCourse.setStudentName(rsStudent.getString("student_name"));
						studentCourse.setStudentEmail(rsStudent.getString("email"));
						
						studentCourseList.add(studentCourse);
				
			}
			
			}
			
			return studentCourseList;
		}
		catch(Exception e)
		{
			throw e;
		}


	}
	
	
	public String addCourse(Course course) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("INSERT INTO  course (Course_id, Course_Name, Course_Start_Date, Course_End_Date, Instructor_Id) VALUES (?, ?, ?, ?, ?)");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			
			java.util.Date date = sdf1.parse(course.getStartDate());
			java.sql.Date startDate = new java.sql.Date(date.getTime());  
			 date = sdf1.parse(course.getEndDate());
			java.sql.Date endDate = new java.sql.Date(date.getTime());  
			
			ps.setString(1,course.getCourseId());
			ps.setString(2,course.getCourseName());
			ps.setDate(3,startDate);
			ps.setDate(4, endDate);
			ps.setString(5, course.getInstructorId());
			
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
	
	
	public String deleteCourse(String courseId) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("DELETE FROM course WHERE Course_id = ?");
			
			ps.setString(1,courseId);
			
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
	
	public String addStudentToCourse(StudentCourse obj) throws Exception
	{
		int returnValue = 0;
		PreparedStatement psAct = 
				connection.prepareStatement("SELECT max(student_course_id) as pid FROM student_course;");
		ResultSet rs = psAct.executeQuery();
		int actId = 0;
		
		while(rs.next())
		 actId = rs.getInt("pid");
		
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("INSERT INTO  student_course (Student_Id, Course_Id, student_course_id, Course_name) VALUES (?, ?, ?, ?)");

			
			ps.setString(1,obj.getStudentId());
			ps.setString(2,obj.getCourseId());
			ps.setInt(3,actId+1);
			ps.setString(4, obj.getCourseName());
			
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
	
	
	
	public String deleteStudentCourse(StudentCourse sc) throws Exception
	{
		int returnValue = 0;
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("DELETE FROM student_course WHERE Student_Id = ? and Course_Id = ?");
			
			ps.setString(1,sc.getStudentId());
			ps.setString(2,sc.getCourseId());
			
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
