'use strict';

angular.module('app').controller('ctrlAdmin', ['$scope', '$rootScope', '$location', 'adminService', function($scope, $rootScope, $location, adminService) {
    var self = this;

    self.students = [];
    self.instructors = [];
    
    	adminService.fetchAllDetails()
            .then(
            function(data) {
            	for(var obj in data.studentList) {
            		
            		var student = {
            	    		 "studentId":data.studentList[obj].studentId,
            	    		 "studentName":data.studentList[obj].studentName,
            	    		 "studentEmail":data.studentList[obj].email,
            	    		 "studentDept":data.studentList[obj].department
                	};
            		 self.students.push(student);
            	}
            	
             	for(var obj in data.instructorList) {
                		
                		var instructor = {
                	    		 "instructorId":data.instructorList[obj].instructorId,
                	    		 "instructorName":data.instructorList[obj].instructorName,
                	    		 "instructorEmail":data.instructorList[obj].email,
                	    		 "instructorDept":data.instructorList[obj].department
                    	};
                		 self.instructors.push(instructor);
            	}
             	
            	},
        		
            function(errResponse){
                console.error('Error while fetching Instructor Details in Controller');
            }
        );
    	
    	
    	function initialize(data) {
    		
    	    self.students = [];
    	    self.instructors = [];
    		
    	    for(var obj in data.studentList) {
        		
        		var student = {
        	    		 "studentId":data.studentList[obj].studentId,
        	    		 "studentName":data.studentList[obj].studentName,
        	    		 "studentEmail":data.studentList[obj].email,
        	    		 "studentDept":data.studentList[obj].department
            	};
        		 self.students.push(student);
        	}
        	
         	for(var obj in data.instructorList) {
            		
            		var instructor = {
            	    		 "instructorId":data.instructorList[obj].instructorId,
            	    		 "instructorName":data.instructorList[obj].instructorName,
            	    		 "instructorEmail":data.instructorList[obj].email,
            	    		 "instructorDept":data.instructorList[obj].department
                	};
            		 self.instructors.push(instructor);
        	}
    	    
    		
    	}
    	
    	function getAllDetails () {
        	adminService.fetchAllDetails()
            .then(
            function(data) {
            	initialize(data);
            },
            function(errResponse){
                console.error('Error while fetching Instructor Details in Controller');
            }
        );
    	} 
    	
    	
    	self.addStudentAdmin = function() {
    		if(self.studentId != null && self.studentPassword != null && self.studentName != null && self.studentEmail != null && self.studentDept != null) {
    		var student = {
        			"studentId":self.studentId,
    	    		 "password":self.studentPassword,
    	    		 "studentName":self.studentName,
    	    		 "email":self.studentEmail,
    	    		 "department": self.studentDept
        	};
    		
    		adminService.createStudent(student)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while adding a student');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    		else  self.Error = false;
    	}

     	self.rmvStudentAdmin = function(studentId) {
    		adminService.deleteStudent(studentId)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while removing a student');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    	
       	self.updateStudentAdmin = function() {
       		if(self.updateStudentId != null && self.updateStudentPassword != null && self.updateStudentName != null && 
       				self.updateStudentEmail != null && self.updateStudentDept != null && self.updateStudentIdActual != null) {

    			var student = {
        			"studentId":self.updateStudentId,
    	    		 "password":self.updateStudentPassword,
    	    		 "studentName":self.updateStudentName,
    	    		 "email":self.updateStudentEmail,
    	    		 "department": self.updateStudentDept
        	};
    			
    		adminService.updateStudent(student, self.updateStudentIdActual)
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
    	
       	
       	self.addInstructorAdmin = function() {
    		if(self.instructorId != null && self.instructorPassword != null && self.instructorName != null && self.instructorEmail != null && self.instructorDept != null) {
    		
    			
    		var instructor = {
        			 "instructorId":self.instructorId,
    	    		 "password":self.instructorPassword,
    	    		 "instructorName":self.instructorName,
    	    		 "email":self.instructorEmail,
    	    		 "department":self.instructorDept
        	};
    			
    		adminService.createInstructor(instructor)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while adding a instructor');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    		else  self.Error = false;
    	}
       	
       	
    	self.removeInstructorAdmin = function(instructorId) {
    		adminService.deleteInstructor(instructorId)
            .then(
            function(d) {
            	self.Success = false;
            	self.Error = true;
            	getAllDetails();
            },
            function(errResponse){
                console.error('Error while removing a instructor');
                self.Error = false;
                self.Success = true;
            }
        );
    		
    	}
    	
       	self.updateInstructorAdmin = function() {
       		if(self.updateInstructorId != null && self.updateInstructorPassword != null && self.updateInstructorName != null && 
       				self.updateInstructorEmail != null && self.updateInstructorDept != null && self.updateInstructorIdActual != null) {

       			var instructor = {
           			 "instructorId":self.updateInstructorId,
       	    		 "password":self.updateInstructorPassword,
       	    		 "instructorName":self.updateInstructorName,
       	    		 "email":self.updateInstructorEmail,
       	    		 "department":self.updateInstructorDept
           	};
    		adminService.updateInstructor(instructor, self.updateInstructorIdActual)
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
    	
       	
    	
    	
    	
    
    
    angular.element(document).ready(function () {
        
        self.addStudentClass = "active";
        self.removeStudentClass = "";
        self.updateStudentClass = "";
        self.addInstructorClass = "";
        self.removeInstructorClass = "";
        self.updateInstructorClass = "";
        
        self.addStudent = false;
        self.rmvStudent = true;
        self.updateStudent = true;
        self.addInstructor = true;
        self.rmvInstructor = true;
        self.updateInstructor = true;
        
        
        self.Error = true;
        self.Success = true;
    });
    
    
    self.addStudentClick = function() {

        self.addStudentClass = "active";
        self.removeStudentClass = "";
        self.updateStudentClass = "";
        self.addInstructorClass = "";
        self.removeInstructorClass = "";
        self.updateInstructorClass = "";
        
        self.addStudent = false;
        self.rmvStudent = true;
        self.updateStudent = true;
        self.addInstructor = true;
        self.rmvInstructor = true;
        self.updateInstructor = true;
        
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.rmvStudentClick = function() {

        self.addStudentClass = "";
        self.removeStudentClass = "active";
        self.updateStudentClass = "";
        self.addInstructorClass = "";
        self.removeInstructorClass = "";
        self.updateInstructorClass = "";
        
        self.addStudent = true;
        self.rmvStudent = false;
        self.updateStudent = true;
        self.addInstructor = true;
        self.rmvInstructor = true;
        self.updateInstructor = true;
        
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.updateStudentClick = function() {

        self.addStudentClass = "";
        self.removeStudentClass = "";
        self.updateStudentClass = "active";
        self.addInstructorClass = "";
        self.removeInstructorClass = "";
        self.updateInstructorClass = "";
        
        self.addStudent = true;
        self.rmvStudent = true;
        self.updateStudent = false;
        self.addInstructor = true;
        self.rmvInstructor = true;
        self.updateInstructor = true;
        
        
        self.Error = true;
        self.Success = true;
    }

    self.addInstructorClick = function() {

        self.addStudentClass = "";
        self.removeStudentClass = "";
        self.updateStudentClass = "";
        self.addInstructorClass = "active";
        self.removeInstructorClass = "";
        self.updateInstructorClass = "";
        
        self.addStudent = true;
        self.rmvStudent = true;
        self.updateStudent = true;
        self.addInstructor = false;
        self.rmvInstructor = true;
        self.updateInstructor = true;
        
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.removeInstructorClick = function() {

        self.addStudentClass = "";
        self.removeStudentClass = "";
        self.updateStudentClass = "";
        self.addInstructorClass = "";
        self.removeInstructorClass = "active";
        self.updateInstructorClass = "";
        
        self.addStudent = true;
        self.rmvStudent = true;
        self.updateStudent = true;
        self.addInstructor = true;
        self.rmvInstructor = false;
        self.updateInstructor = true;
        
        
        self.Error = true;
        self.Success = true;
    	
    }
    
    self.updateInstructorClick = function() {

        self.addStudentClass = "";
        self.removeStudentClass = "";
        self.updateStudentClass = "";
        self.addInstructorClass = "";
        self.removeInstructorClass = "";
        self.updateInstructorClass = "active";
        
        self.addStudent = true;
        self.rmvStudent = true;
        self.updateStudent = true;
        self.addInstructor = true;
        self.rmvInstructor = true;
        self.updateInstructor = false;
        
        
        self.Error = true;
        self.Success = true;
    }
    
    
}]);


