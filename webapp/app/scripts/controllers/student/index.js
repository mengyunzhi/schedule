'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentIndexCtrl
 * @description
 * # StudentIndexCtrl
 * 学生管理 界面
 */
angular.module('scheduleApp')
    .controller('StudentIndexCtrl', function($scope, $http, $state, studentService) {
        var self = this;
        self.init = function() {
            $scope.query = {};
            $scope.query.name = '';
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

        self.removeStudent = function(id) {
            $scope.studens = $scope.students.filter(function(_student) {
                if (_student.id === id) {
                    return false;
                } else {
                    return true;
                }
            });
        };

        //删除
        self.delete = function(object) {
            studentService.delete(object, function() {
                self.removeStudent(object.id);
                $state.reload();

            });
        };

        // 按学生姓名查询 
        self.findByName = function() {
            if ($scope.query.name === '') {
                self.reload();
            } else {
                studentService.findByName($scope.query.name, function(students) {
                    $scope.students = students;
                });
            }
        };

        self.init()
        $scope.delete = self.delete;
        $scope.changeState = self.changeState;
        $scope.findByName = self.findByName;
    });
