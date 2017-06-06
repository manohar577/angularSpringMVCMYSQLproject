'use strict';

angular.module('app').factory('activityService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/beangrinderUI/activity/';

    var factory = {
    		fetchAllActivities: fetchAllActivities
    };
    return factory;
    
    function fetchAllActivities(courseId) {
    	var deferred = $q.defer();
    	$http({method: 'post', url: REST_SERVICE_URI+"getAllActivities", data:courseId,cache: false}).success(function(data, status, headers, config) {
        	deferred.resolve(data); 
        }).error(function(data, status, headers, config) { 
        	console.error('Error while fetching courses list');
        		deferred.reject("Invalid courseId or something");
        		}); 
        	return deferred.promise;
        }

}]);
