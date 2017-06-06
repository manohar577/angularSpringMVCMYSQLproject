'use strict';

angular.module('app').factory('adminService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/beangrinderUI/admin/';

    var factory = {
    		
    		fetchAllDetails: fetchAllDetails,
    		createStudent:createStudent,
    		deleteStudent:deleteStudent,
    		updateStudent:updateStudent,
    		createInstructor:createInstructor,
    		deleteInstructor:deleteInstructor,
    		updateInstructor:updateInstructor
    		
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
    
    
    function createStudent(student) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/addStudent", student)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Student');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteStudent(studentId) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/removeStudent", studentId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting student');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function updateStudent(student, studentId) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/updateStudent/"+studentId, student)
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
    
    
    function createInstructor(instructor) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/addInstructor", instructor)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating instructor');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function deleteInstructor(instructorId) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/deleteInstructor", instructorId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting instructor');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function updateInstructor(instructor, instructorId) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"/updateInstructor/"+instructorId, instructor)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating instructor');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
        }
    

}]);
