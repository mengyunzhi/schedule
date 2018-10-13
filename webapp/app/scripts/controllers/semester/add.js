'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:SemesterAddCtrl
 * @description
 * # SemesterAddCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
  .controller('SemesterAddCtrl', function ($scope, $state, semester) {
  	var self = this;
  	self.init = function() {
  		$scope.semester = {};
  	};
  	self.add = function() {
  		semester.add($scope.semester, function() {
  			$state.go('semester', {}, {reload: true});
  		}, function() {
  			self.init();
  		});
  	};
  	self.init();
  	$scope.add = self.add;
  });
