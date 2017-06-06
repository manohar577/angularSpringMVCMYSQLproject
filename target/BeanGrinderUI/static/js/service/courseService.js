'use strict';

angular.module('app').factory('courseService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/beangrinderUI/course/';

    var factory = {
    		fetchAllCourses: fetchAllCourses
    };
    return factory;
    
    function fetchAllCourses() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"getAllCourses")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching courses list');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


}]);
