'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:CourseAddCtrl
 * @description
 * # CourseAddCtrl
 * Controller of the scheduleApp
 * @author chenjie
 */
angular.module('scheduleApp')
    .controller('CourseAddCtrl', function($scope, $http, $state, courseService) {
        var self = this;

        //初始化
        self.init = function() {
                $scope.data = {
                    name: '',
                    semester: {}
                };
        };

        //提交add信息
        self.submit = function() {
            courseService.add($scope.data, function() {
                $state.transitionTo('course', {}, { reload: true });
            });
        };

        self.init();
        $scope.submit = self.submit;
    });
