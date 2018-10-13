'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:SemesterEditCtrl
 * @description
 * # SemesterEditCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
  .controller('SemesterEditCtrl', function ($scope, $state, $filter, semester) {
    var self = this;
    $scope.semester = {};
    self.init = function() {
    	var id = $state.params.id;
    	semester.getById(id, function(data) {
    		$scope.semester = data;
    	});
    };
    self.submit = function() {
    	semester.update($scope.semester.id, $scope.semester, function() {
    		$state.go('semester', {}, {reload: true});
    	});
    };
    self.init();
    $scope.submit = self.submit;
  });
