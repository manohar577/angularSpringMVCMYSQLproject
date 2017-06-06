'use strict';

angular.module('app').controller('ctrlTask', ['$scope', '$rootScope', '$location', 'taskService', function($scope, $rootScope, $location, taskService) {
    var self = this;

    self.activityId = $rootScope.activityId;
    self.activityName = $rootScope.activityName;
    self.tasks = [];
    
    taskService.fetchAllTasks(self.activityId)
            .then(
            function(data) {
            	for(var obj in data) {
            		
            		var task = {
                			 "taskId":data[obj].taskId,
                			 "activityId":data[obj].activityId,
            	    		 "startDate":data[obj].startDate,
            	    		 "EndDate":data[obj].endDate,
            	    		 "taskName":data[obj].taskName,
            	    		 "taskStatement":data[obj].taskStatement
                	};
            		 self.tasks.push(task);
            	}
        	                 
            },
            function(errResponse){
                console.error('Error while fetching tasks');
            }
        );
    	
    	self.getTask = function(task) {
    		$rootScope.task = task;
    		$location.path("/exec");
    		
    	}
    	
}]);


