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
            });
        };

        self.init();
        $scope.toSelectWeekOrder = self.toSelectWeekOrder;
    });
