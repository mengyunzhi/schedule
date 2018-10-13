'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.semester
 * @description
 * # semester
 * Service in the scheduleApp.
 */
angular.module('scheduleApp')
  .service('semester', function ($http, $filter) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    var self = this;
    var baseUrl = '/semester';
    self.add = function(semester, callBack, failBack) {
    	var addSemester = {};
    	addSemester.name = semester.name;
    	addSemester.startTime = $filter('iosToStringFormat')(semester.startTime);
    	addSemester.endTime = $filter('iosToStringFormat')(semester.endTime);
  		$http.post(baseUrl + '/', addSemester)
  		.then(function() {
  			callBack();
  		}, function(response) {
  			console.log(response);
  			failBack();
  		});
    };
    self.getAll = function(callBack) {
    	$http.get(baseUrl + '/')
    	.then(function(response) {
    		callBack(response.data);
    	}, function() {
    		console.log('fail to get all semester');
    	});
    };
    self.delete = function(semester, callBack) {
    	$http.delete(baseUrl + '/' + semester.id)
    	.then(function() {
    		callBack();
    	}, function() {
    		console.log('false to delete semester');
    	});
    };
    self.active = function(id, callBack) {
    	$http.put(baseUrl + '/active/' + id)
    	.then(function() {
    		callBack();
    	}, function() {
    		console.log('fail to active semester');
    	});
    };
    self.getById = function(id, callBack) {
    	$http.get(baseUrl + '/' + id)
    	.then(function(response) {
    		var data = response.data;
    		data.startTime = new Date(parseInt(data.startTime));
    		data.endTime = new Date(parseInt(data.endTime));
    		callBack(data);
    	}, function() {
    		console.log('false to get one semester');
    	});
    };
    self.update = function(id, semester, callBack) {
    	var addSemester = {};
    	addSemester.name = semester.name;
    	addSemester.startTime = $filter('iosToStringFormat')(semester.startTime);
    	addSemester.endTime = $filter('iosToStringFormat')(semester.endTime);
    	addSemester.status = semester.status;
    	$http.put(baseUrl + '/' + id, addSemester)
    	.then(function() {
    		callBack();
    	}, function() {
    		console.log('false to update semester');
    	});
    };
    self.findByName = function(name, callBack) {
    	$http.get(baseUrl + '/name/' + name)
    	.then(function(response) {
    		callBack(response.data);
    	}, function() {
    		console.log('false to find semester by name');
    	});
    };
  });