'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:SemesterIndexCtrl
 * @description
 * # SemesterIndexCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('SemesterIndexCtrl', function($scope, $state, semester) {
        var self = this;
        self.init = function() {
            $scope.query = {};
            $scope.query.name = '';
            self.reload();
        };
        self.reload = function() {
            semester.getAll(function(data) {
                $scope.semesters = data;
            });
        };
        self.delete = function(ob) {
            semester.delete(ob, function() {
                $state.go('semester', {}, { reload: true });
            });
        };
        self.active = function(ob) {
            semester.active(ob.id, function() {
                self.reload();
            });
        };
        self.findByName = function() {
            if ($scope.query.name == '') {
                self.reload();
            } else {
                semester.findByName($scope.query.name, function(data) {
                    $scope.semesters = data;
                });
            }
        };
        self.init();
        $scope.delete = self.delete;
        $scope.active = self.active;
        $scope.findByName = self.findByName;
    });
