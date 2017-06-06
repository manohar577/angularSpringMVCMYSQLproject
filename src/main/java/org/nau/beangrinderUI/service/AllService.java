package org.nau.beangrinderUI.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.nau.beangrinderUI.model.*;



public interface AllService {
	

	String findStudentById(Student obj) throws Exception;

	List<Course> fetchAllCourses(String studentId) throws Exception;

	List<Activity> fetchAllActivities(String courseId);

	List<Task> fetchAllTasks(String activityId);

	String findInstructorById(Student obj);

	HashMap<String, Object> fetchAllDetails(String instructorId);

	String addCourse(Course obj);

	String deleteCourse(String id);

	String deleteActivity(String id);

	String addActivity(Activity obj);

	String addTask(Task obj);

	String deleteTask(String id);

	String addStudentToCourse(StudentCourse obj);

	String deleteStudentCourse(StudentCourse studentCourse);

	String getAdmin(Student obj);

	HashMap<String, Object> fetchAllDetailsAdmin(String admin);

	String addStudentAdmin(Student obj);

	String removeStudentAdmin(String studentId);

	String updateStudentAdmin(String studentId, Student student);

	String addInstructorAdmin(Instructor obj);

	String removeInstructorAdmin(String instructorId);

	String updateInstructorAdmin(String instructorId, Instructor instructor);
	
}
