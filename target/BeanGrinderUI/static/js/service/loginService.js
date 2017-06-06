'use strict';

angular.module('app').factory('loginService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/beangrinderUI/student/';

    var factory = {
        getUser: getUser
    };
    
    self.student={studentId:null,studentName:'',department:'',password:'',email:''};
    return factory;

    function getUser(id, password) {
    var deferred = $q.defer();
    self.student.studentId = id;
    self.student.password = password;
    	

    $http({method: 'post', url: REST_SERVICE_URI+id, data:self.student,cache: false}).success(function(data, status, headers, config) {
    	// resolving the promise
    	deferred.resolve(data); 
    }).error(function(data, status, headers, config) { 

    		deferred.reject("Invalid UserName or Password");
    		}); 
    	return deferred.promise;
    }


}]);
