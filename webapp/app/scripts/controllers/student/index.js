'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentIndexCtrl
 * @description
 * # StudentIndexCtrl
 * 学生管理
 */
angular.module('scheduleApp')
    .controller('StudentIndexCtrl', function($scope, $http, studentService) {
        var self = this;
        self.init = function() {

            self.reload();
        };

        self.reload = function(students) {
            studentService.getAllStudent(function(students) {
                $scope.students = students;
            });
        };

        self.changeState = function(students) {
            var url = '/student/state/' + students.id;
            $http.put(url, $scope.students)
                .then(function success() {
                    self.reload();
                }, function error() {
                    console.log('errer');
                });
        };

        self.init()
        $scope.changeState = self.changeState;

    });
