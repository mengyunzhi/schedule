'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentSelectcourseCtrl
 * @description
 * # StudentSelectcourseCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('StudentSelectcourseCtrl', function($http, $state, $scope, $stateParams, courseService, studentService) {
        var self = this;

        // 初始化
        self.init = function() {
            self.selectAllOrNot = true;
            $scope.params = { page: 0, size: 3 };
            self.load();
        };

        // 加载数据
        self.load = self.reload = function() {
            courseService.page($scope.params, function(data) {
                $scope.data = data;
                // 将所有课程状态初始化为false
                angular.forEach($scope.data.content, function(course) {
                    course._checked = false;
                });
                self.selectActive();
            });
        };

        // 当前学生已选课程默认选中
        self.selectActive = function() {
            var id = $stateParams.id;
            studentService.getStudentByCourse(id, function(data) {
                // 循环所有课程
                angular.forEach($scope.data.content, function(course) {
                    // 循环当前学生所选的课程
                    angular.forEach(data.courseList, function(checkedCourse) {
                        // 判断当前学生课程与所有课程中相同的课程
                        if (course.id === checkedCourse.id) {
                            //默认选中已选课程
                            course._checked = true;
                        }
                    });
                });
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

        // 选课
        self.selectCourse = function() {
            var id = $stateParams.id;
            var array = $scope.data.content.filter(function(_course) {
                return _course._checked;
            });
            studentService.selectCourse(id, array, function() {
                $state.transitionTo('student', {}, { reload: true });
            });
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
            angular.forEach($scope.data.content, function(_course) {
                _course._checked = true;
            });

        };

        //全不选
        self.removeAll = function() {
            angular.forEach($scope.data.content, function(_course) {
                _course._checked = false;
            });
        };

        $scope.select = self.select;
        $scope.selectCourse = self.selectCourse;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        self.init();
    });
