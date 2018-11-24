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
        // 初始化
        self.init = function() {
            $scope.params = { page: 0, size: 3 };
            $scope.query = {};
            $scope.query.name = '';
            $scope.query.selectSemester = {};
            self.getCurrentSemester();
            $scope.selectAllOrNot = false;
            $scope.$watch('data.content', self.watchCourse);
        };

        // 加载数据
        self.load = self.reload = function() {
            courseService.page($scope.params, function(data) {
                // 获取到所有的课程
                $scope.data = data;
                self.initSelect($scope.data.content);
                $scope.selectAllOrNot = false;
            });
        };

        // 将所有课程的选择状态初始化为false
        self.initSelect = function(data) {
            angular.forEach(data, function(course) {
                course._checked = false;
            });
        };

        // 获取当前学期
        self.getCurrentSemester = function() {
            courseService.getCurrentSemester(function(currentSemester) {
                // 将当前学期存到服务中去
                courseService.currentSemester = currentSemester;

                // 将当前学期传给页面
                $scope.currentSemester = currentSemester;

                $scope.params.semesterId = currentSemester.id;

                self.load();
            });
        };

        // 分页时重新加载数据
        self.reloadByPage = function(page) {
            $scope.params.page = page;
            self.reloadData();
        };

        // 进行每页大小
        self.reloadBySize = function(size) {
            $scope.params.size = size;
            self.reloadData();
        };

        // 批量删除
        self.deleteMultiple = function() {
            var deleteList = [];
            angular.forEach($scope.data.content, function(course) {
                if (course._checked) {
                    delete course._checked;
                    deleteList.push(course);
                }
            });
            courseService.deleteMultiple(deleteList, function() {
                self.reloadData();
                $scope.selectAllOrNot = false;

            })
        };

        // 全选
        self.select = function() {
            $scope.selectAllOrNot = !$scope.selectAllOrNot;
            angular.forEach($scope.data.content, function(course) {
                course._checked = $scope.selectAllOrNot;
            });
        };

        //监听全部课程 _cheched 变化
        self.watchCourse = function(newValue) {
            if (newValue) {
                var count = 0;
                angular.forEach(newValue, function(value) {
                    if (value._checked) {
                        count++;
                    }
                });
                if (count != 0) {
                    $scope.selectAllOrNot = count === newValue.length;
                }
            }
        };

        // 根据学期ID和课程名查询
        self.findBySemesterIdAndName = function() {
            var semesterId = $scope.query.selectSemester.id;
            var courseName = $scope.query.name;          
            $scope.params.semesterId = semesterId;
            courseService.query(semesterId, courseName, $scope.params, function(data) {
                $scope.data = data;
                self.initSelect($scope.data.content);
            });
        };

        // 点击查询
        $scope.find = function() {
            $scope.params = {page: 0, size: 3};
            self.findBySemesterIdAndName();
        };

        // 重新加载查询数据或者当前学期数据
        self.reloadData = function() {
            if ($scope.query.name || $scope.query.selectSemester != $scope.currentSemester) {
                self.findBySemesterIdAndName();
            } else { self.reload(); }
        };

        self.init();
        $scope.select = self.select;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        $scope.deleteMultiple = self.deleteMultiple;
        $scope.findBySemesterIdAndName = self.findBySemesterIdAndName;
    });
