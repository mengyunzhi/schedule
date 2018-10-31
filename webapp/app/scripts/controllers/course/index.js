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
            self.selectAllOrNot = true;
            $scope.params = { page: 0, size: 10 };
            $scope.query = {};
            $scope.query.name = '';
            $scope.query.selectSemester = {};
            self.load();
            self.getCurrentSemester();
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
            angular.forEach($scope.data, function(_list) {
                _list._checked = true;
            });

        };

        //全不选
        self.removeAll = function() {
            angular.forEach($scope.data, function(_list) {
                _list._checked = false;
            });
        };

        // 批量删除
        self.deleteMultiple = function() {
            var deleteList = $scope.data.filter(function(_list) {
                return _list._checked;
            });

            angular.forEach(deleteList, function(deleteIems, key) {
                delete deleteIems._checked;
            });
            courseService.deleteMultiple(deleteList, function() {
                self.reload();
            })
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
        $scope.selectAll = self.selectAll;
        $scope.select = self.select;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        $scope.deleteMultiple = self.deleteMultiple;
        $scope.findBySemesterIdAndName = self.findBySemesterIdAndName;

    });
