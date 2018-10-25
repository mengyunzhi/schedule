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

        self.toSelectWeekOrder = function(week, node) {
            var url = 'course.selectWeekOrder';
            $state.go(url, {
                args: {
                    id: $scope.data.id,
                    week: week,
                    node: node,
                    semesterId: $scope.data.semester.id
                }
            });
        };

        self.init();
        $scope.toSelectWeekOrder = self.toSelectWeekOrder;
    });
