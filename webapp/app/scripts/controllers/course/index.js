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
            $scope.params = { page: 0, size: 10 };
            $scope.query = {};
            $scope.query.name = '';
            $scope.query.selectSemester = {};
            self.load();
            self.getCurrentSemester();
            $scope.selectAllOrNot = false;
            $scope.$watch('data.content', self.watchCourse, true);
        };

        // 加载数据
        self.load = self.reload = function() {
            courseService.page($scope.params, function(data) {
                // 获取到所有的课程
                $scope.data = data;     
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
                // 获取当前学期的课程
                self.findCurrentSemesterCourse();
            });
        };

        self.findCurrentSemesterCourse = function() {
            var id = courseService.currentSemester.id;
            var name = '';
            courseService.query(id, name, function(data) {
                $scope.data.content = data;
                self.initSelect($scope.data.content);
            });
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
                self.reload();
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
            console.log($scope.query.selectSemester);
            console.log($scope.query.name);
            var semesterId = $scope.query.selectSemester.id;
            var courseName = $scope.query.name;
            courseService.query(semesterId, courseName, function(data) {
                $scope.data.content = data;
                self.initSelect($scope.data.content);
            });
        };

        self.init();
        $scope.select = self.select;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        $scope.deleteMultiple = self.deleteMultiple;
        $scope.findBySemesterIdAndName = self.findBySemesterIdAndName;
    });
