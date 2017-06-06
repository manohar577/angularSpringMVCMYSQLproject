'use strict';

angular.module('app').controller('ctrlCourse', ['$scope', '$rootScope', '$location', 'courseService', function($scope, $rootScope, $location, courseService) {
    var self = this;

    self.courses = [];
    
    	courseService.fetchAllCourses()
            .then(
            function(data) {
            	var count = 0;
            	
            	for(var obj in data) {
            		var course = {
                			"courseId":data[obj].courseId,
            	    		 "courseName":data[obj].courseName,
            	    		 "startDate":data[obj].startDate,
            	    		 "EndDate":data[obj].endDate,
            	    		 "instructorId":data[obj].instructorId,
            	    		 "title":data[obj].courseName ,
            	    		 "content":data[obj].courseId,
                	};
            		 self.courses.push(course);
            	}
        	                 
            },
            function(errResponse){
                console.error('Error while fetching courses');
            }
        );
    	
    	/*self.toggleAcc = function() {
    		var acc = document.getElementsByClassName("accordion");
    		var i;

    		for (i = 0; i < acc.length; i++) {
    		    acc[i].onclick = function(){
    		        this.classList.toggle("active");
    		        var panel = this.nextElementSibling;
    		        if (panel.style.display === "block") {
    		            panel.style.display = "none";
    		        } else {
    		            panel.style.display = "block";
    		        }
    		    }
    		}
    	}*/
    	
    	self.courseClick = function(courseId, courseName) {
    		$rootScope.courseId = courseId;
    		$rootScope.courseName = courseName;
    		$location.path("/activity");
    		
    	}
    	
    	
}]);


