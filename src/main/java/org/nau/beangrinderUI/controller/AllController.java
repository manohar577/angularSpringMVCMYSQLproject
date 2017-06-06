package org.nau.beangrinderUI.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.nau.beangrinderUI.model.Activity;
import org.nau.beangrinderUI.model.Course;
import org.nau.beangrinderUI.model.Instructor;
import org.nau.beangrinderUI.model.Student;
import org.nau.beangrinderUI.model.StudentCourse;
import org.nau.beangrinderUI.model.Task;
import org.nau.beangrinderUI.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 
@RestController
public class AllController {
 
    @Autowired
    AllService serviceObj;  
    
    @Autowired 
    private HttpSession httpSession;
 
    
    
    //-------------------Retrieve Single Student or Instructor details--------------------------------------------------------
     
    @RequestMapping(value = "/student/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String,Object>> getUser(@RequestBody Student obj) {
        String id = null;
        HashMap<String,Object> returnMap =  new HashMap<String,Object>();
		try {
			id = serviceObj.findStudentById(obj);
		
				if (id == null) { 
						id = getInstructor(obj);
							if( id == null  ) {
								id = serviceObj.getAdmin(obj);
								if(id == null) {
									return new ResponseEntity<HashMap<String,Object>>(returnMap, HttpStatus.NOT_FOUND);
								}
								else {
					            	httpSession.setAttribute("admin", id);
					            	returnMap.put("admin", id);
								}
							}
							else { 
					            	httpSession.setAttribute("InstructorId", id);
					            	returnMap.put("InstructorId", id);
			            	}
				}
				else {
		        httpSession.setAttribute("studentId", id);
		        returnMap.put("studentId", id);
		
		        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<HashMap<String,Object>>(returnMap, HttpStatus.OK);
    }
    
    

	public String getInstructor(Student obj) {
    	String id = null;
    	try {
			id = serviceObj.findInstructorById(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return id;
    }
    
    
    //-------------------Retrieve Instructor Details --------------------------------------------------------
    
    @RequestMapping(value = "/instructor/getAllDetails", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String,Object>> listAllDetails() {
    	 HashMap<String,Object> returnMap =  new HashMap<String,Object>();
		try {
			String instructorId = (String)httpSession.getAttribute("InstructorId");
			returnMap = serviceObj.fetchAllDetails(instructorId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnMap.isEmpty()){
            return  new ResponseEntity<HashMap<String,Object>>(returnMap, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<HashMap<String,Object>>(returnMap, HttpStatus.OK);
    }
    
 
    //-------------------Retrieve courses --------------------------------------------------------
    
    @RequestMapping(value = "/course/getAllCourses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> listAllCourses() {
        List<Course> Course = new ArrayList<Course>();
		try {
			String studentId = (String)httpSession.getAttribute("studentId");
			Course = serviceObj.fetchAllCourses(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(Course.isEmpty()){
            return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Course>>(Course, HttpStatus.OK);
    }

    
    //-------------------Retrieve Activities --------------------------------------------------------
    
    @RequestMapping(value = "/activity/getAllActivities", method = RequestMethod.POST)
    public ResponseEntity<List<Activity>> listAllActivites(@RequestBody String courseId) {
        List<Activity> activityList = new ArrayList<Activity>();
		try {
			String studentId = (String)httpSession.getAttribute("studentId");
			activityList = serviceObj.fetchAllActivities(courseId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(activityList.isEmpty()){
            return new ResponseEntity<List<Activity>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Activity>>(activityList, HttpStatus.OK);
    }
    
    //-------------------Retrieve Tasks --------------------------------------------------------
    
    @RequestMapping(value = "/tasks/getAllTasks", method = RequestMethod.POST)
    public ResponseEntity<List<Task>> listAllTasks(@RequestBody String activityId) {
        List<Task> taskList = new ArrayList<Task>();
		try {
			String studentId = (String)httpSession.getAttribute("studentId");
			taskList = serviceObj.fetchAllTasks(activityId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(taskList.isEmpty()){
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(taskList, HttpStatus.OK);
    }
    
    
    //-------------------ADD COURSE --------------------------------------------------------
    
    @RequestMapping(value = "/instructor/addCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCourse(@RequestBody Course obj) {
    	String returnValue = null;
		try {
			String instructorId = (String)httpSession.getAttribute("InstructorId");
			if(instructorId != null) {
			obj.setInstructorId(instructorId);
			returnValue = serviceObj.addCourse(obj);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    
    //-------------------ADD Activity --------------------------------------------------------
    
    @RequestMapping(value = "/instructor/addActivity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addActivity(@RequestBody Activity obj) {
    	String returnValue = null;
		try {
			String instructorId = (String)httpSession.getAttribute("InstructorId");
			if(instructorId != null) {
			returnValue = serviceObj.addActivity(obj);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }    
    
    //-------------------ADD Task --------------------------------------------------------
    
    @RequestMapping(value = "/instructor/addTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addTask(@RequestBody Task obj) {
    	String returnValue = null;
		try {
			String instructorId = (String)httpSession.getAttribute("InstructorId");
			if(instructorId != null) {
			returnValue = serviceObj.addTask(obj);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    } 
    
    
    //-------------------ADD Task --------------------------------------------------------
    @RequestMapping(value = "/instructor/addStudentToCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudentToCourse(@RequestBody StudentCourse obj) {
    	String returnValue = null;
		try {
			String instructorId = (String)httpSession.getAttribute("InstructorId");
			if(instructorId != null) {
			returnValue = serviceObj.addStudentToCourse(obj);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    //-------------------DELETE COURSE --------------------------------------------------------   
    @RequestMapping(value = "/instructor/deleteCourse", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCourse(@RequestBody String id) {
    	String returnValue = null;
		try {
			returnValue = serviceObj.deleteCourse(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    //-------------------DELETE Activity --------------------------------------------------------   
    @RequestMapping(value = "/instructor/deleteActivity", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteActivity(@RequestBody String id) {
    	String returnValue = null;
		try {
			returnValue = serviceObj.deleteActivity(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    //-------------------DELETE Task --------------------------------------------------------   
    @RequestMapping(value = "/instructor/deleteTask", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteTask(@RequestBody String id) {
    	String returnValue = null;
		try {
			returnValue = serviceObj.deleteTask(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    //-------------------DELETE Student --------------------------------------------------------   
    @RequestMapping(value = "/instructor/deleteStudentCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteStudentCourse(@RequestBody StudentCourse studentCourse) {
    	String returnValue = null;
		try {
			returnValue = serviceObj.deleteStudentCourse(studentCourse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    //-------------------Retrieve ALl Details for Admin--------------------------------------------------------
    
    @RequestMapping(value = "/admin/getAllDetails", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String,Object>> getAllDetails() {
    	 HashMap<String,Object> returnMap =  new HashMap<String,Object>();
		try {
			String admin = (String)httpSession.getAttribute("admin");
			returnMap = serviceObj.fetchAllDetailsAdmin(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnMap.isEmpty()){
            return  new ResponseEntity<HashMap<String,Object>>(returnMap, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<HashMap<String,Object>>(returnMap, HttpStatus.OK);
    }
    
    
  //-------------------Add Student Admin--------------------------------------------------------
    
    @RequestMapping(value = "/admin/addStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String,Object>> addStudentAdmin(@RequestBody Student obj) {
    	
    	String returnValue = null;
		try {
			String adminId = (String)httpSession.getAttribute("admin");
			if(adminId != null) {
			returnValue = serviceObj.addStudentAdmin(obj);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    
  //-------------------Remove Student Admin--------------------------------------------------------
    
    @RequestMapping(value = "/admin/removeStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String,Object>> removeStudentAdmin(@RequestBody String studentId) {
    	String returnValue = null;
		try {
			String adminId = (String)httpSession.getAttribute("admin");
			if(adminId != null) {
			returnValue = serviceObj.removeStudentAdmin(studentId);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    
  //-------------------Update Student Admin--------------------------------------------------------
    
    @RequestMapping(value = "/admin/updateStudent/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String,Object>> updateStudentAdmin(@PathVariable("id") String studentId, @RequestBody Student student) {
    	String returnValue = null;
		try {
			String adminId = (String)httpSession.getAttribute("admin");
			if(adminId != null) {
			returnValue = serviceObj.updateStudentAdmin(studentId, student);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
 //-------------------Add Student Admin--------------------------------------------------------
    
    @RequestMapping(value = "/admin/addInstructor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String,Object>> addInstructorAdmin(@RequestBody Instructor obj) {
    	
    	String returnValue = null;
		try {
			String adminId = (String)httpSession.getAttribute("admin");
			if(adminId != null) {
			returnValue = serviceObj.addInstructorAdmin(obj);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    
  //-------------------Remove Student Admin--------------------------------------------------------
    
    @RequestMapping(value = "/admin/deleteInstructor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String,Object>> removeInstructorAdmin(@RequestBody String instructorId) {
    	String returnValue = null;
		try {
			String adminId = (String)httpSession.getAttribute("admin");
			if(adminId != null) {
			returnValue = serviceObj.removeInstructorAdmin(instructorId);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    
  //-------------------Update Student Admin--------------------------------------------------------
    
    @RequestMapping(value = "/admin/updateInstructor/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String,Object>> updateInstructorAdmin(@PathVariable("id") String instructorId, @RequestBody Instructor instructor) {
    	String returnValue = null;
		try {
			String adminId = (String)httpSession.getAttribute("admin");
			if(adminId != null) {
			returnValue = serviceObj.updateInstructorAdmin(instructorId, instructor);
			}
			else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(returnValue == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    
    
    //-------------------logout --------------------------------------------------------
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout() {
    	String returnValue = null;
		try {
			 httpSession.invalidate();
			 returnValue = "session invalidated";
		} catch (Exception e) {
			e.printStackTrace();
		}
        if(returnValue == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity (HttpStatus.OK);
    }
}