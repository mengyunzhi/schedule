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
            $scope.params = { page: 0, size: 5 };
            $scope.query = {};
            $scope.query.name = '';
            self.reload();
        };

        // 加载数据
        self.reload = function(students) {
            studentService.page($scope.params, function(data) {
                $scope.data = data;
            });
        };

        //分页时重新加载数据
        self.reloadPage = function(page) {
            $scope.params.page = page;
            self.reload();
            self.findByName();
        };

        //选择每页大小时重新加载数据
        self.reloadBySize = function(size) {
            $scope.params.size = size;
            self.reload();
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

        //删除
        self.removeStudent = function(id) {
            $scope.studens = $scope.data.content.filter(function(_student) {
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
                studentService.findByName($scope.query.name, $scope.params, function(data) {
                    $scope.data = data;
                });
            }
        };

        $scope.delete = self.delete;
        $scope.changeState = self.changeState;
        $scope.findByName = self.findByName;
        $scope.reloadPage = self.reloadPage;
        $scope.reloadBySize = self.reloadBySize;
        self.init();
    });
