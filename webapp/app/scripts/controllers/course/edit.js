'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:CourseEditCtrl
 * @description
 * # CourseEditCtrl
 * Controller of the scheduleApp
 * @author chenjie
 */
angular.module('scheduleApp')
    .controller('CourseEditCtrl', function($scope, $stateParams, $state, courseService) {
        var self = this;

        // 获取当前页面数据
        self.init = function() {
            var id = $stateParams.id;
            courseService.getCourseById(id, function(data) {
                //绑定数据导V层
                $scope.data = data;
            });
        };

        // 提交数据
        self.submit = function() {
            var id = $stateParams.id;
            courseService.update(id, $scope.data, function() {
              // 提交成功，进行跳转
               $state.transitionTo('course', {}, { reload: true });
            });                             
        };

        self.init();
        $scope.submit = self.submit;
    });
