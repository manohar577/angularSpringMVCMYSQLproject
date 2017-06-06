'use strict';

angular.module('app').factory('taskService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/beangrinderUI/tasks/';

    var factory = {
    		fetchAllTasks: fetchAllTasks
    };
    return factory;
    
    function fetchAllTasks(activityId) {
    	var deferred = $q.defer();
    	$http({method: 'post', url: REST_SERVICE_URI+"getAllTasks", data:activityId,cache: false}).success(function(data, status, headers, config) {
        	deferred.resolve(data); 
        }).error(function(data, status, headers, config) { 
        	console.error('Error while fetching task list');
        		deferred.reject("Invalid activityId or something");
        		}); 
        	return deferred.promise;
        }

}]);
