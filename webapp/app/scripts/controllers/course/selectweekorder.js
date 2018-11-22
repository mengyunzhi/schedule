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
        
        // 获取数据
        self.init = function() {
            $scope.weekOrders = [];
            $scope.selectAllOrNot = false;
            self.courseId = $stateParams.courseId;
            self.week = $stateParams.week;
            self.node = $stateParams.node;
            self.semesterId = $stateParams.semesterId;
            self.getCourse();
            self.getSemester();
            $scope.$watch('weekOrders', self.watchWeekOrders, true);
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
                self.getWeekOrders();
            });
        };

        // 获取最大周次
        self.getWeekOrders = function() {
            var startTime = $scope.semester.startTime;
            var endTime = $scope.semester.endTime;
            self.maxWeekOrder = schedule.getWeekOder(startTime, endTime);
            self.generateWeekOrders();
            self.defaultSelect();

        };

        // 生成周次列表,默认非选中
        self.generateWeekOrders = function() {
            for (var i = 0; i < self.maxWeekOrder; i++) {
                $scope.weekOrders[i] = {};
                $scope.weekOrders[i].value = i + 1;
                $scope.weekOrders[i]._checked = false;
            }
        };

        // 当前课程已选周次默认选中
        self.defaultSelect = function() {
            courseService.getCourseById(self.courseId, function(data) {
                // 已选中的周次
                var selectWeekOrders = [];
                angular.forEach(data.scheduleList, function(list) {
                    if (list.week == self.week && list.node == self.node) {
                        selectWeekOrders.push(list.weekOrder);
                    }
                });

                // 循环所有周次
                angular.forEach($scope.weekOrders, function(weekorder) {
                    // 循环当前学生所选的周次
                    angular.forEach(selectWeekOrders, function(selectWeekOrder) {
                        // 判断当前课程已选周次与所有周次中相同的周次
                        if (selectWeekOrder == weekorder.value) {
                            //默认选中已选课程
                            weekorder._checked = true;
                        }
                    });
                });
            });
        };


        // 全选/全不选
        self.select = function() {
            $scope.selectAllOrNot = !$scope.selectAllOrNot;
            angular.forEach($scope.weekOrders, function(weekorder) {
                weekorder._checked = $scope.selectAllOrNot;
            });
        };

        //监听全部周次 _cheched 变化
        self.watchWeekOrders = function(newValue) {
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

        // 提交数据
        self.submit = function() {
            var selectWeekOrders = [];
            // 筛选出已选中的周次
            angular.forEach($scope.weekOrders, function(weekorder) {
                if (weekorder._checked) {
                    selectWeekOrders.push(weekorder.value);
                }
            });
            // 发起请求
            courseService.selectSchedule(
                self.courseId,
                self.semesterId,
                self.week,
                self.node,
                selectWeekOrders,
                function() {
                    // 提交成功，进行跳转
                    var url = 'course.selectSchedule';
                    console.log(url);
                    $state.transitionTo(url, {id: self.courseId}, { reload: true });
                });
        };

        self.init();
        $scope.week = self.week;
        $scope.node = self.node;
        $scope.submit = self.submit;
        $scope.select = self.select;
    });
