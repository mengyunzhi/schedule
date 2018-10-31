'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:CourseSelectweekorderCtrl
 * @description
 * # CourseSelectweekorderCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('CourseSelectweekorderCtrl', function($scope, courseService, $stateParams, semester, schedule, $state) {
        var self = this;
        $scope.weekOrders = [];
        $scope.selectOrNot = false;
        self.selectWeekOrders = [];

        // 获取数据
        self.init = function() {
            console.log($stateParams);
            self.courseId = $stateParams.courseId;
            self.week = $stateParams.week;
            self.node = $stateParams.node;
            self.semesterId = $stateParams.semesterId;
            self.getCourse();
            self.getSemester();

        };

        // 获取课程
        self.getCourse = function() {
            courseService.getCourseById(self.courseId, function(data) {
                // 绑定数据到V层
                $scope.course = data;
            });
        };

        // 获取学期
        self.getSemester = function() {
            semester.getById(self.semesterId, function(data) {
                $scope.semester = data;
                console.log($scope.semester);
                self.getWeekOrders();
            });
        };

        // 获取最大周次
        self.getWeekOrders = function() {
            var startTime = $scope.semester.startTime;
            var endTime = $scope.semester.endTime;
            self.maxWeekOrder = schedule.getWeekOder(startTime, endTime);
            self.generateWeekOrders();
        };

        // 生成周次列表
        self.generateWeekOrders = function() {
            for (var i = 0; i < self.maxWeekOrder; i++) {
                $scope.weekOrders[i] = i + 1;
            }
        };

        // 将周次添加到已选周次数组中
        self.pushOrRemove = function(value, selectOrNot) {
            if (selectOrNot === false) {
                self.selectWeekOrders.push(value);
            } else {
                self.selectWeekOrders.removeByValue(value);
            }
        };

        // 根据value删除数组特定元素
        self.selectWeekOrders.removeByValue = function(value) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] === value) {
                    this.splice(i, 1);
                    break;
                }
            }
        };

        // 提交数据
        self.submit = function() {
            console.log(self.courseId);
            console.log(self.week);
            console.log(self.node);
            console.log(self.selectWeekOrders);
            console.log(self.semesterId);
            courseService.selectSchedule(
                self.courseId,
                self.semesterId,
                self.week,
                self.node,
                self.selectWeekOrders,
                function() {
                    // 提交成功，进行跳转
                    // $state.transitionTo('course', {}, { reload: true });
                });
        };

        self.init();
        $scope.weekList = self.weekList;
        $scope.nodeList = self.nodeList;
        $scope.week = self.week;
        $scope.node = self.node;
        $scope.push = self.push;
        $scope.submit = self.submit;
        $scope.pushOrRemove = self.pushOrRemove;
    });
