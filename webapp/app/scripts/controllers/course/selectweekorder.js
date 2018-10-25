'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:CourseSelectweekorderCtrl
 * @description
 * # CourseSelectweekorderCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('CourseSelectweekorderCtrl', function($scope, courseService, $stateParams) {
        var self = this;
        self.init = function() {
            var courseId = $stateParams.args.id;
            var week = $stateParams.args.week;
            var node = $stateParams.args.node;
            var semesterId = $stateParams.args.semesterId;
            courseService.getCourseById(courseId, function(data) {
                //绑定数据导V层
                $scope.course = data;
            });
        };
        self.init();
    });
