package org.nau.beangrinderUI.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.nau.beangrinderUI.dao.ActivityDao;
import org.nau.beangrinderUI.dao.AdminDao;
import org.nau.beangrinderUI.dao.CourseDao;
import org.nau.beangrinderUI.dao.Database;
import org.nau.beangrinderUI.dao.InstructorDao;
import org.nau.beangrinderUI.dao.StudentDao;
import org.nau.beangrinderUI.dao.TaskDao;
import org.nau.beangrinderUI.model.Activity;
import org.nau.beangrinderUI.model.Admin;
import org.nau.beangrinderUI.model.Course;
import org.nau.beangrinderUI.model.Instructor;
import org.nau.beangrinderUI.model.Student;
import org.nau.beangrinderUI.model.StudentCourse;
import org.nau.beangrinderUI.model.Task;

@Service("allService")
public class AllServiceImpl implements AllService{
    /*@Autowired
    StudentDao studentDao; 
	*/
	@Autowired
	HttpSession httpSession;
	
	StudentDao studentDao = new StudentDao();
	InstructorDao instructorDao = new InstructorDao();
	CourseDao courseDao = new CourseDao();
	ActivityDao activityDao = new ActivityDao();
	TaskDao taskDao = new TaskDao();
	AdminDao adminDao = new AdminDao();
	@Override
	public String findStudentById(Student obj) {
		Student student = null;
		try {
			student =studentDao.getStudentDetails(obj.getStudentId(), obj.getPassword());
			return student.getStudentId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String findInstructorById(Student obj) {
		Instructor instructor = null;
		try {
			instructor =instructorDao.getInstructorDetails(obj.getStudentId(), obj.getPassword());
			return instructor.getInstructorId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getAdmin(Student obj) {

		Admin admin = new Admin();
		try {
			admin = adminDao.getAdminDetails(obj.getStudentId(),obj.getPassword());
			return admin.getAdminId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public HashMap<String, Object> fetchAllDetails(String instructorId) {
		 HashMap<String,Object> returnMap =  new HashMap<String,Object>();
		 
		 List<Course> courseList = new ArrayList<Course>();
		 List<Activity> activityList = new ArrayList<Activity>();
		 List<Task> taskList = new ArrayList<Task>();
		 List<StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
		 List<Student> studentList = new ArrayList<Student>();
		 
		 
		 try {
		
			 studentList.addAll(studentDao.getAllStudentDetails());
			 courseList.addAll(courseDao.getCourseDetailsByInstructor(instructorId));
			 
			 for(Course course : courseList){
			
			 activityList.addAll(activityDao.getActivityDetails(course.getCourseId()));
			 
			 for(Activity activity : activityList) 
				 taskList.addAll(taskDao.getTaskDetails(activity.getActivityId()));
			 
			 studentCourseList.addAll(courseDao.getStudentCourseDetailsByCourse(course.getCourseId(), course.getCourseName()));
			 }
			 
			 returnMap.put("studentList", studentList);
			 returnMap.put("courseList", courseList);
			 returnMap.put("studentCourseList", studentCourseList);
			 returnMap.put("activityList", activityList);
			 returnMap.put("taskList", taskList);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnMap;
	}
	
	@Override
	public List<Course> fetchAllCourses(String studentId) throws Exception {
		List<Course> courseList = new ArrayList<Course>(); 
		try {
			courseList = courseDao.getCourseDetails(studentId);
			return courseList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Activity> fetchAllActivities(String courseId){
		List<Activity> activityList = new ArrayList<Activity>(); 
		try {
			activityList = activityDao.getActivityDetails(courseId);
			return activityList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Task> fetchAllTasks(String activityId){
		List<Task> taskList = new ArrayList<Task>(); 
		try {
			taskList = taskDao.getTaskDetails(activityId);
			return taskList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String addCourse(Course obj) {
		String ret = null;
		try {
			ret = courseDao.addCourse(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	
	@Override
	public String deleteCourse(String id) {
		String ret = null;
		try {
			ret = courseDao.deleteCourse(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	
	@Override
	public String addActivity(Activity obj) {
		String ret = null;
		try {
			ret = activityDao.addActivity(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public String deleteActivity(String id) {
		String ret = null;
		try {
			ret = activityDao.deleteActivity(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	@Override
	public String addTask(Task obj) {
		String ret = null;
		try {
			ret = taskDao.addTask(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public String deleteTask(String id) {
		String ret = null;
		try {
			ret = taskDao.deleteTask(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public String addStudentToCourse(StudentCourse obj) {
		String ret = null;
		try {
			ret = courseDao.addStudentToCourse(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public String deleteStudentCourse(StudentCourse studentCourse) {
		String ret = null;
		try {
			ret = courseDao.deleteStudentCourse(studentCourse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	
	
	
	
	@Override
	public HashMap<String, Object> fetchAllDetailsAdmin(String adminId) {
		 HashMap<String,Object> returnMap =  new HashMap<String,Object>();
		 
		 List<Instructor> instructorList = new ArrayList<Instructor>();
		 List<Student> studentList = new ArrayList<Student>();
		 
		 
		 try {
		
			 studentList.addAll(studentDao.getAllStudentDetails());
			 instructorList.addAll(instructorDao.getAllInstructorDetails());
			 
			 
			 returnMap.put("studentList", studentList);
			 returnMap.put("instructorList", instructorList);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnMap;
	}

	@Override
	public String addStudentAdmin(Student obj) {
	
		String ret = null;
		try {
			ret = studentDao.addStudent(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public String removeStudentAdmin(String id) {
		String ret = null;
		try {
			ret = studentDao.deleteStudentAdmin(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String updateStudentAdmin(String studentId, Student student) {
		String ret = null;
		try {
			ret = studentDao.updateStudentAdmin(studentId, student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String addInstructorAdmin(Instructor obj) {
		String ret = null;
		try {
			ret = instructorDao.addInstructorAdmin(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String removeInstructorAdmin(String instructorId) {
		String ret = null;
		try {
			ret = instructorDao.deleteInstructorAdmin(instructorId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String updateInstructorAdmin(String instructorId,
			Instructor instructor) {
		String ret = null;
		try {
			ret = instructorDao.updateInstructorAdmin(instructorId, instructor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
