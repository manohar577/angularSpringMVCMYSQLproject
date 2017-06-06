'use strict';

angular.module('app').controller('ctrlInstructor', ['$scope', '$rootScope', '$location', 'instructorService', function($scope, $rootScope, $location, instructorService) {
    var self = this;

    self.courses = [];
    self.activities = [];
    self.tasks = [];
    self.studentCourse = [];
    self.students = [];
    
    	instructorService.fetchAllDetails()
            .then(
            function(data) {
            	for(var obj in data.courseList) {
            		
            		
            		var course = {
                			"courseId":data.courseList[obj].courseId,
            	    		 "courseName":data.courseList[obj].courseName,
            	    		 "startDate":data.courseList[obj].startDate,
            	    		 "EndDate":data.courseList[obj].endDate,
            	    		 "instructorId":data.courseList[obj].instructorId,
                	};
            		 self.courses.push(course);
            	}
            	
             	for(var obj in data.activityList) {
            		var activity = {
                			 "courseId":data.activityList[obj].courseId,
                			 "activityId":data.activityList[obj].activityId,
            	    		 "startDate":data.activityList[obj].startDate,
            	    		 "EndDate":data.activityList[obj].endDate,
            	    		 "activityName":data.activityList[obj].activityName,
            	    		 "activityDetails":data.activityList[obj].activityDetails
                	};
            		 self.activities.push(activity);
            	}
             	
             	for(var obj in data.taskList) {
            		
            		var task = {
                			 "taskId":data.taskList[obj].taskId,
                			 "activityId":data.taskList[obj].activityId,
            	    		 "startDate":data.taskList[obj].startDate,
            	    		 "EndDate":data.taskList[obj].endDate,
            	    		 "taskName":data.taskList[obj].taskName,
            	    		 "taskStatement":data.taskList[obj].taskStatement
                	};
            		 self.tasks.push(task);
            	}
             	
             	
             	for(var obj in data.studentCourseList) {
            		
            		var studentCourse = {
                			 "courseId":data.studentCourseList[obj].courseId,
                			 "courseName":data.studentCourseList[obj].courseName,
            	    		 "studentEmail":data.studentCourseList[obj].studentEmail,
            	    		 "studentId":data.studentCourseList[obj].studentId,
            	    		 "studentName":data.studentCourseList[obj].studentName
                	};
            		 self.studentCourse.push(studentCourse);
            	}
             	
             	for(var obj in data.studentList) {
            		
            		var student = {
            	    		 "studentId":data.studentList[obj].studentId,
            	    		 "studentName":data.studentList[obj].studentName,
            	    		 "studentEmail":data.studentList[obj].email,
            	    		 "studentDept":data.studentList[obj].department
                	};
            		 self.students.push(student);
            	}
        		
            },
            function(errResponse){
                console.error('Error while fetching Instructor Details in Controller');
            }
        );
    	
    	
    	function initialize(data) {
    		
    	    self.courses = [];
    	    self.activities = [];
    	    self.tasks = [];
    	    self.studentCourse = [];
    		
    		for(var obj in data.courseList) {
        		
        		
        		var course = {
            			"courseId":data.courseList[obj].courseId,
        	    		 "courseName":data.courseList[obj].courseName,
        	    		 "startDate":data.courseList[obj].startDate,
        	    		 "EndDate":data.courseList[obj].endDate,
        	    		 "instructorId":data.courseList[obj].instructorId,
            	};
        		 self.courses.push(course);
        	}
        	
         	for(var obj in data.activityList) {
        		var activity = {
            			 "courseId":data.activityList[obj].courseId,
            			 "activityId":data.activityList[obj].activityId,
        	    		 "startDate":data.activityList[obj].startDate,
        	    		 "EndDate":data.activityList[obj].endDate,
        	    		 "activityName":data.activityList[obj].activityName,
        	    		 "activityDetails":data.activityList[obj].activityDetails
            	};
        		 self.activities.push(activity);
        	}
         	
         	for(var obj in data.taskList) {
        		
        		var task = {
            			 "taskId":data.taskList[obj].taskId,
            			 "activityId":data.taskList[obj].activityId,
        	    		 "startDate":data.taskList[obj].startDate,
        	    		 "EndDate":data.taskList[obj].endDate,
        	    		 "taskName":data.taskList[obj].taskName,
        	    		 "taskStatement":data.taskList[obj].taskStatement
            	};
        		 self.tasks.push(task);
        	}
         	
         	
         	for(var obj in data.studentCourseList) {
        		
        		var studentCourse = {
            			 "courseId":data.studentCourseList[obj].courseId,
            			 "courseName":data.studentCourseList[obj].courseName,
        	    		 "studentEmail":data.studentCourseList[obj].studentEmail,
        	    		 "studentId":data.studentCourseList[obj].studentId,
        	    		 "studentName":data.studentCourseList[obj].studentName
            	};
        		 self.studentCourse.push(studentCourse);
        	}
         	
        	for(var obj in data.studentList) {
        		
        		var student = {
        	    		 "studentId":data.studentList[obj].studentId,
        	    		 "studentName":data.studentList[obj].studentName,
        	    		 "studentEmail":data.studentList[obj].email,
        	    		 "studentDept":data.studentList[obj].department
            	};
        		 self.students.push(student);
        	}
    		
    	}
    	
    	function getAllDetails () {
        	instructorService.fetchAllDetails()
            .then(
            function(data) {
            	initialize(data);
            },
            function(errResponse){
                console.error('Error while fetching Instructor Details in Controller');
            }
        );
    	} 
    	
    	
    	self.courseSubmitClick = function() {
    		if(self.courseId != null && self.courseName != null && self.startDate != null && self.endDate != null) {
    			var startMonth = self.startDate.getMonth()+1;
    			var endMonth = self.endDate.getMonth()+1
    		var course = {
        			"courseId":self.courseId,
    	    		 "courseName":self.courseName,
    	    		 "startDate":self.startDate.getDate()+"-"+startMonth+"-"+self.startDate.getFullYear(),
    	    		 "endDate":self.startDate.getDate()+"-"+endMonth+"-"+self.startDate.getFullYear()
        	};
    		instructorService.createCourse(course)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while adding a course');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    		else  self.Error = false;
    	}
    	
    	
       	self.activitySubmitClick = function() {
    		if(self.actCourseId != null && self.ActivityName != null && self.actStartDate != null && self.actEndDate != null && self.ActivityDetails != null ) {
    			var startMonth = self.actStartDate.getMonth()+1;
    			var endMonth = self.actEndDate.getMonth()+1
    		var activity = {
        			"courseId":self.actCourseId,
    	    		 "activityName":self.ActivityName,
    	    		 "activityDetails":self.ActivityDetails,
    	    		 "startDate":self.actStartDate.getDate()+"-"+startMonth+"-"+self.actStartDate.getFullYear(),
    	    		 "endDate":self.actEndDate.getDate()+"-"+endMonth+"-"+self.actEndDate.getFullYear()
        	};
    			
    		instructorService.createActivity(activity)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while adding a activity');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    		else  self.Error = false;
    	}
    	
       	
       	self.taskSubmitClick = function() {
    		if(self.taskActId != null && self.taskName != null && self.taskStatement != null && self.taskSolution != null && self.taskStartDate != null
    				&& self.taskEndDate != null ) {
    			var startMonth = self.taskStartDate.getMonth()+1;
    			var endMonth = self.taskEndDate.getMonth()+1
    		var task = {
        			 "activityId":self.taskActId.split("-")[0].trim(),
    	    		 "taskName":self.taskName,
    	    		 "taskStatement":self.taskStatement,
    	    		 "taskSolution":self.taskSolution,
    	    		 "startDate":self.taskStartDate.getDate()+"-"+startMonth+"-"+self.taskStartDate.getFullYear(),
    	    		 "endDate":self.taskEndDate.getDate()+"-"+endMonth+"-"+self.taskEndDate.getFullYear()
        	};
    			
    		instructorService.createTask(task)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while adding a task');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    		else  self.Error = false;
    	}
       	
       	
       	self.addStudent = function() {
    		if(self.addStudentId != null && self.addCourseId != null) {
    			
    			var studentObj = "";
    			var courseObj = "";
    			for(var obj in self.students) {
    				if(self.students[obj].studentId == self.addStudentId.split("-")[0].trim())
    					studentObj = self.students[obj];
    			}
    			for(var obj in self.courses) {
    				if(self.courses[obj].courseId == self.addCourseId.split("-")[0].trim())
    					courseObj = self.courses[obj];
    			}
    			

    		var studentCourse = {
    				 "courseId":courseObj.courseId,
        			 "courseName":courseObj.courseName,
    	    		 "studentEmail":studentObj.email,
    	    		 "studentId":studentObj.studentId,
    	    		 "studentName":studentObj.studentName
        	};
    			
    		instructorService.addStudentToCourse(studentCourse)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while adding a task');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    		else  self.Error = false;
    	}
       	
       	self.removeStudentClick = function(student) {
    		instructorService.deleteStudentCourse(student)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while removing a student for a course');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
       	
     	self.courseRemoveClick = function(courseId) {
    		instructorService.deleteCourse(courseId)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while removing a course');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    	
    	self.removeActivityClick = function(activityId) {
    		instructorService.deleteActivity(activityId)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while removing a Activity');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    	
    	self.removeTaskClick = function(taskId) {
    		instructorService.deleteTask(taskId)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while removing a Task');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    	
    	
    	
    
    
    angular.element(document).ready(function () {
        
        self.addCourseClass = "active";
        self.removeCourseClass = "";
        self.addActClass = "";
        self.removeActClass = "";
        self.addTaskClass = "";
        self.removeTaskClass = "";
        self.addStdClass = "";
        self.removestdClass = "";
        
        self.addCourse = false;
        self.rmvCourse = true;
        self.addAct = true;
        self.rmvAct = true;
        self.addTask = true;
        self.rmvTask = true;
        self.addStd = true;
        self.rmvStd = true;
        
        
        self.Error = true;
        self.Success = true;
    });
    
    
    self.addCourseClick = function() {

        self.addCourseClass = "active";
        self.removeCourseClass = "";
        self.addActClass = "";
        self.removeActClass = "";
        self.addTaskClass = "";
        self.removeTaskClass = "";
        self.addStdClass = "";
        self.removestdClass = "";
        
        self.addCourse = false;
        self.rmvCourse = true;
        self.addAct = true;
        self.rmvAct = true;
        self.addTask = true;
        self.rmvTask = true;
        self.addStd = true;
        self.rmvStd = true;
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.rmvCourseClick = function() {

        self.addCourseClass = "";
        self.removeCourseClass = "active";
        self.addActClass = "";
        self.removeActClass = "";
        self.addTaskClass = "";
        self.removeTaskClass = "";
        self.addStdClass = "";
        self.removestdClass = "";
        
        self.addCourse = true;
        self.rmvCourse = false;
        self.addAct = true;
        self.rmvAct = true;
        self.addTask = true;
        self.rmvTask = true;
        self.addStd = true;
        self.rmvStd = true;
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.addActClick = function() {

        self.addCourseClass = "";
        self.removeCourseClass = "";
        self.addActClass = "active";
        self.removeActClass = "";
        self.addTaskClass = "";
        self.removeTaskClass = "";
        self.addStdClass = "";
        self.removestdClass = "";
        
        self.addCourse = true;
        self.rmvCourse = true;
        self.addAct = false;
        self.rmvAct = true;
        self.addTask = true;
        self.rmvTask = true;
        self.addStd = true;
        self.rmvStd = true;
        
        self.Error = true;
        self.Success = true;
    }

    self.rmvActClick = function() {

        self.addCourseClass = "";
        self.removeCourseClass = "";
        self.addActClass = "";
        self.removeActClass = "active";
        self.addTaskClass = "";
        self.removeTaskClass = "";
        self.addStdClass = "";
        self.removestdClass = "";
        
        self.addCourse = true;
        self.rmvCourse = true;
        self.addAct = true;
        self.rmvAct = false;
        self.addTask = true;
        self.rmvTask = true;
        self.addStd = true;
        self.rmvStd = true;
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.addTaskClick = function() {

        self.addCourseClass = "";
        self.removeCourseClass = "";
        self.addActClass = "";
        self.removeActClass = "";
        self.addTaskClass = "active";
        self.removeTaskClass = "";
        self.addStdClass = "";
        self.removestdClass = "";
        
        self.addCourse = true;
        self.rmvCourse = true;
        self.addAct = true;
        self.rmvAct = true;
        self.addTask = false;
        self.rmvTask = true;
        self.addStd = true;
        self.rmvStd = true;
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.rmvTaskClick = function() {

        self.addCourseClass = "";
        self.removeCourseClass = "";
        self.addActClass = "";
        self.removeActClass = "";
        self.addTaskClass = "";
        self.removeTaskClass = "active";
        self.addStdClass = "";
        self.removestdClass = "";
        
        self.addCourse = true;
        self.rmvCourse = true;
        self.addAct = true;
        self.rmvAct = true;
        self.addTask = true;
        self.rmvTask = false;
        self.addStd = true;
        self.rmvStd = true;
        
        self.Error = true;
        self.Success = true;
    }
    
    self.addStdClick = function() {
    	
        self.addCourseClass = "";
        self.removeCourseClass = "";
        self.addActClass = "";
        self.removeActClass = "";
        self.addTaskClass = "";
        self.removeTaskClass = "";
        self.addStdClass = "active";
        self.removestdClass = "";
        
        self.addCourse = true;
        self.rmvCourse = true;
        self.addAct = true;
        self.rmvAct = true;
        self.addTask = true;
        self.rmvTask = true;
        self.addStd = false;
        self.rmvStd = true;
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.rmvStdClick = function() {
    	
        self.addCourseClass = "";
        self.removeCourseClass = "";
        self.addActClass = "";
        self.removeActClass = "";
        self.addTaskClass = "";
        self.removeTaskClass = "";
        self.addStdClass = "";
        self.removestdClass = "active";
        
        self.addCourse = true;
        self.rmvCourse = true;
        self.addAct = true;
        self.rmvAct = true;
        self.addTask = true;
        self.rmvTask = true;
        self.addStd = true;
        self.rmvStd = false;
        
        self.Error = true;
        self.Success = true;
    }
    
    
}]);


