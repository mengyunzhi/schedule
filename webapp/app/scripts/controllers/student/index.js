'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentIndexCtrl
 * @description
 * # StudentIndexCtrl
 * 学生管理 界面
 */
angular.module('scheduleApp')
    .controller('StudentIndexCtrl', function($scope, $http, studentService) {
        var self = this;
        self.init = function() {

            self.reload();
        };

        //获取所有学生
        self.reload = function(students) {
            studentService.getAllStudent(function(students) {
                $scope.students = students;
            });
        };

        //点击改变学生状态
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
