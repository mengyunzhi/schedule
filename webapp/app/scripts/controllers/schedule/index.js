'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ScheduleIndexCtrl
 * @description
 * # ScheduleIndexCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('ScheduleIndexCtrl', function($scope, $http, schedule) {

        var self = this;

        /**
         * 初始化行程 仅仅执行一次
         */
        self.init = function() {
            $scope.schedules = schedule.initSechedules(); //将行程集合初始化为一个7*5的数组

            $scope.error = false; //当学期获取失败显示错误信息

            $scope.query = {}; //查询条件

            //当获取学期为空时显示错误信息
            //否则加载行程集合 更新查询条件
            schedule.getCurrentSemester(function(data) {
                if (data == null) {
                    $scope.error = true;
                } else {
                    $scope.semester = data;
                    $scope.query.maxWeekOrder = schedule.getWeekOder($scope.semester.startTime, $scope.semester.endTime);
                    $scope.query.weekOrder = schedule.getWeekOder($scope.semester.startTime);
                    self.reload();
                }
            });
        };

        /**
         * 根据学期和查询条件获取行程集合
         * 对集合进行加工转换为7*5的数组
         * 为每个行程添加学生数组
         */
        self.reload = function() {
            if ($scope.semester != null) {
                if ($scope.query.weekOrder == null) {
                    $scope.query.weekOrder = schedule.getWeekOder($scope.semester.startTime);
                }
                //从后台获取行程集合
                schedule.getnowschedules($scope.semester.id, $scope.query.weekOrder, function(data) {
                    if (data.length != 0) {
                        var schedules = data;

                        // $http.put('/schedule/init/16')
                        // .then(function(){},function(){});
                        

                        //为每个行程添加学生数组
                        schedule.addScheduleMessage(schedules);
                        //对集合进行加工转换为7*5的数组
                        $scope.schedules = schedule.createSechedules(schedules);
                    } else {
                        $scope.schedules = schedule.initSechedules();
                    }
                });
            }
        };

        self.init();
        $scope.reload = self.reload;
    });
