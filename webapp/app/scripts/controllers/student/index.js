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
                console.log(students);
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
            $scope.student = $scope.student.filter(function(_student) {
                if (_student.id === id) {
                    return false;
                } else {
                    return true;
                }
            });
        };

        //删除
        self.delete = function(object) {
            studentService.delete(object, function(){
                self.removeStudent(object.id);
                console.log(23132132)
            });
        };
    
        self.init()
        $scope.delete = self.delete;
        $scope.changeState = self.changeState;
    });
