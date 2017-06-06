'use strict';

angular.module('app').controller('ctrlExecTask', ['$scope', '$rootScope', '$location', 'execTaskService', function($scope, $rootScope, $location, execTaskService) {
    var self = this;

    self.task = $rootScope.task;
    
    
    self.runCompile = function() {
    	
    	if (self.resultCode != undefined) {
    		
    		
    	}
    }
    

    angular.element(document).ready(function () {
        
        self.code = true;
        self.unitTest = true;
        self.probStmt = false;
        
        self.psClass = "active";
        self.codeClass = "";
        self.utClass = "";
    });
    
    
    self.psClick = function() {
        self.probStmt = false;
        self.code = true;
        self.unitTest = true;
        
        self.psClass = "active";
        self.codeClass = "";
        self.utClass = "";
    	
    }
    
    self.codeClick = function() {
        self.probStmt = true;
        self.code = false;
        self.unitTest = true;
        
        self.psClass = "";
        self.codeClass = "active";
        self.utClass = "";
    	
    }
    
    self.utClick = function() {
        self.probStmt = true;
        self.code = true;
        self.unitTest = false;
    	
        self.psClass = "";
        self.codeClass = "";
        self.utClass = "active";
    }
    
}]);


