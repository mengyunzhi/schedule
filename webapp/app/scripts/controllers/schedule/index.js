'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ScheduleIndexCtrl
 * @description
 * # ScheduleIndexCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('ScheduleIndexCtrl', function($scope, schedule) {
        var self = this;

        self.init = function() {
            //init schedules array
            $scope.schedules = schedule.initSechedules();

            $scope.error = false;

            $scope.query = {};

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

        self.reload = function() {
            if ($scope.semester != null) {
                if ($scope.query.weekOrder == null) {
                    $scope.query.weekOrder = schedule.getWeekOder($scope.semester.startTime);
                }
                schedule.getnowschedules($scope.semester.id, $scope.query.weekOrder, function(data) {
                    $scope.schedules = data;
                });
            }
        };

        self.init();
        $scope.reload = self.reload;
    });
