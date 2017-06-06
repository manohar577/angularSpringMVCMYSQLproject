'use strict';

angular.module('app').controller('ctrlActivity', ['$scope', '$rootScope', '$location', 'activityService', function($scope, $rootScope, $location, activityService) {
    var self = this;

    self.courseId = $rootScope.courseId;
    self.courseName = $rootScope.courseName;
    self.activities = [];
    
    activityService.fetchAllActivities(self.courseId)
            .then(
            function(data) {
            	for(var obj in data) {
            		var activity = {
                			 "courseId":data[obj].courseId,
                			 "activityId":data[obj].activityId,
            	    		 "startDate":data[obj].startDate,
            	    		 "EndDate":data[obj].endDate,
            	    		 "activityName":data[obj].activityName,
            	    		 "activityDetails":data[obj].activityDetails
                	};
            		 self.activities.push(activity);
            	}
        	                 
            },
            function(errResponse){
                console.error('Error while fetching courses');
            }
        );
    	
    	self.getTasks = function(activityId, activityName) {
    		$rootScope.activityId = activityId;
    		$rootScope.activityName = activityName;
    		$location.path("/task");
    		
    	}
    	
}]);


