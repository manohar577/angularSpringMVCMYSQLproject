'use strict';

angular.module('app').controller('ctrlLogin', ['$scope', '$rootScope','$location', 'loginService', 'homeService', function($scope, $rootScope, $location, loginService, homeService) {
    var self = this;
    self.getUser = getUser;

    function getUser(){
    	loginService.getUser(self.studentId, self.studentPass)
            .then(
            function(d) {
           	 $rootScope.logoutShow = false;
                self.id = d;
                if(self.id.InstructorId) {
                $location.path("/instructor");
                $rootScope.InstructorId = self.id.InstructorId;
                }
                if(self.id.admin) {
                    $location.path("/admin");
                    $rootScope.adminId = self.id.adminId;
                    }
                if(self.id.studentId) {
                    $location.path("/course");
                    $rootScope.studentId = self.id.studentId;
                }
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    
    
    $rootScope.logoutClick = function() {

    	homeService.logout()
        .then(
        function(data) {
        	 $location.path("/");
        	 $rootScope.logoutShow = true;
        },
        function(errResponse){
            console.error('Error while logging out the user');
        });
		
    }


}]);


