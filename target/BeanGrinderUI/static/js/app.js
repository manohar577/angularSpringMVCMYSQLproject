'use strict';

var app = angular.module('app',["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
    	templateUrl: 'static/views/login.html',
        controller:	'ctrlLogin',
        controllerAs: 'ctrlLogin'
    })
    .when("/course", {
    	templateUrl: 'static/views/CourseDetails.html',
        controller:	'ctrlCourse',
        controllerAs: 'ctrlCourse'
    })
    .when("/activity", {
    	templateUrl: 'static/views/Activity.html',
        controller:	'ctrlActivity',
        controllerAs: 'ctrlActivity'
    })
    .when("/task", {
    	templateUrl: 'static/views/Tasks.html',
        controller:	'ctrlTask',
        controllerAs: 'ctrlTask'
    })
    .when("/exec", {
    	templateUrl: 'static/views/ExecuteTask.html',
        controller:	'ctrlExecTask',
        controllerAs: 'ctrlExecTask'
    })
    .when("/instructor", {
    	templateUrl: 'static/views/Instructor.html',
        controller:	'ctrlInstructor',
        controllerAs: 'ctrlInstructor'
    })
    .when("/admin", {
    	templateUrl: 'static/views/Admin.html',
        controller:	'ctrlAdmin',
        controllerAs: 'ctrlAdmin'
    });
});


