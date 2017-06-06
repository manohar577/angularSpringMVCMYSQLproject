'use strict';

angular.module('app').factory('instructorService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/beangrinderUI/instructor/';

    var factory = {
    		fetchAllDetails: fetchAllDetails,
    		createCourse:createCourse,
    		deleteCourse:deleteCourse,
    		createActivity:createActivity,
    		deleteActivity:deleteActivity,
    		createTask:createTask,
    		deleteTask:deleteTask,
    		addStudentToCourse:addStudentToCourse,
    		deleteStudentCourse:deleteStudentCourse
    		
    };
    return factory;
    
    function fetchAllDetails() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"getAllDetails")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Insdtructor Details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function createCourse(course) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/addCourse", course)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Course');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function createActivity(activity) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/addActivity", activity)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Activity');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function createActivity(activity) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/addActivity", activity)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Activity');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function createTask(task) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/addTask", task)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Task');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteCourse(id) {
    	var deferred = $q.defer();
        $http({method: 'delete', url: REST_SERVICE_URI+"/deleteCourse", data:id,cache: false}).success(function(data, status, headers, config) {
        	// resolving the promise
        	deferred.resolve(data); 
        }).error(function(data, status, headers, config) { 

        		deferred.reject("Unable to delete Course ");
        		}); 
        	return deferred.promise;
        }
    
    function deleteActivity(id) {
    	var deferred = $q.defer();
        $http({method: 'delete', url: REST_SERVICE_URI+"/deleteActivity", data:id,cache: false}).success(function(data, status, headers, config) {
        	// resolving the promise
        	deferred.resolve(data); 
        }).error(function(data, status, headers, config) { 

        		deferred.reject("Unable to delete activity");
        		}); 
        	return deferred.promise;
        }
    
    function deleteTask(id) {
    	var deferred = $q.defer();
        $http({method: 'delete', url: REST_SERVICE_URI+"/deleteTask", data:id,cache: false}).success(function(data, status, headers, config) {
        	// resolving the promise
        	deferred.resolve(data); 
        }).error(function(data, status, headers, config) { 

        		deferred.reject("Unable to delete task");
        		}); 
        	return deferred.promise;
        }
    
    function addStudentToCourse(studentCourse) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/addStudentToCourse", studentCourse)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while addStudentToCourse');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function deleteStudentCourse(student) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/deleteStudentCourse", student)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Unable to delete deleteStudentCourse');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
