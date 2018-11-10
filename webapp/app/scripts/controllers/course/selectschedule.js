'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:CourseSelectscheduleCtrl
 * @description
 * # CourseSelectscheduleCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('CourseSelectscheduleCtrl', function($scope, $stateParams, courseService, $state) {
        var self = this;
        
        // 获取当前页面的数据
        self.init = function() {
            var id = $stateParams.id;
            courseService.getCourseById(id, function(data) {
                //绑定数据导V层
                $scope.data = data;
                console.log(data.scheduleList);
                self.createForm(data.scheduleList);
                console.log($scope.form[0][0]);
            });
        };

        //课程表中添加周次
        self.createForm = function(schedules) {
            $scope.form = self.initSechedules();
            angular.forEach(schedules, function(schedule, key) {
                var week = schedule.week;
                var node = schedule.node;
                $scope.form[week - 1][node - 1].push(schedule.weekOrder);
            });           
        };

        // 生成一个7X5的空课程表
        self.initSechedules = function() {
            var schedules = [];
            for (var week = 0; week < 7; week++) {
                var nodes = [];
                for (var node = 0; node < 5; node++) {
                    nodes[node] = [];
                }
                schedules[week] = nodes;
            }
            return schedules;
        }

        self.init();
        
        
        $scope.toSelectWeekOrder = self.toSelectWeekOrder;
    });
