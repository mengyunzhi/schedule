'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:CourseIndexCtrl
 * @description
 * # CourseIndexCtrl
 * Controller of the scheduleApp
 * @author     chenjie
 */
angular.module('scheduleApp')
    .controller('CourseIndexCtrl', function($http, $scope, courseService, semester, $filter, schedule) {
        var self = this;
        var courses = [];

        // 初始化
        self.init = function() {
            self.selectAllOrNot = true;
            $scope.params = { page: 0, size: 3 };
            $scope.$watch('selectedSemester', self.handleData);
            self.load();

        };

        // 加载数据
        self.load = self.reload = function() {
            courseService.page($scope.params, function(data) {
                // 获取到所有的课程
                courses = data.content;

                // 将所有课程的选择状态初始化为false
                angular.forEach(courses, function(course) {
                    course._checked = false;
                });

                self.getCurrentSemester();         
            });
        };

        // 获取当前学期
        self.getCurrentSemester = function(callback) {
            courseService.getCurrentSemester(function(currentSemester) {
                courseService.currentSemester = currentSemester;
                // 选择的学期默认为当前学期
                $scope.selectedSemester = courseService.currentSemester;
                if (callback) {callback(courses);}
            });
        };

        self.change = function(selectSemester) {
            $scope.selectSemester = selectSemester;
        };

        // 监听选择学期的变化
        self.handleData = function(newValue) {
            if (newValue && newValue.id) {
                // 执行函数
                console.log('xxx');
            }            
        };

        // 分页时重新加载数据
        self.reloadByPage = function(page) {
            $scope.params.page = page;
            self.reload();
        };

        // 进行每页大小
        self.reloadBySize = function(size) {
            $scope.params.size = size;
            self.load();
        };

        //判断当前是否全选
        self.select = function() {
            if (self.selectAllOrNot) {
                self.selectAll();
            } else {
                self.removeAll();
            }
            self.selectAllOrNot = !self.selectAllOrNot;
        };

        //全选
        self.selectAll = function() {
            angular.forEach($scope.data.content, function(_list) {
                _list._checked = true;
            });

        };

        //全不选
        self.removeAll = function() {
            angular.forEach($scope.data.content, function(_list) {
                _list._checked = false;
            });
        };

        // 批量删除
        self.deleteMultiple = function() {
            var deleteList = $scope.data.content.filter(function(_list) {
                return _list._checked;
            });

            angular.forEach(deleteList, function(deleteIems, key) {
                delete deleteIems._checked;
            });
            courseService.deleteMultiple(deleteList, function() {
                self.reload();
            })
        };


        self.init();
        $scope.selectAll = self.selectAll;
        $scope.select = self.select;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        $scope.deleteMultiple = self.deleteMultiple;


    });
