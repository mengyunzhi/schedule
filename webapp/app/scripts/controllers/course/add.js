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
    .controller('CourseAddCtrl', function($scope, $http, $state) {

        var self = this;

        //初始化
        self.init = function() {
            $scope.data = {
                name: ''
            };
        };

        //提交add信息
        self.submit = function() {
            var url = '/Course/';
            $http.post(url, $scope.data)
                .then(function success(response) {
                    console.log(response);
                    $state.transitionTo('course', {}, { reload: true });

                }, function error() {
                    console.log('请求课程列表发生错误');
                });
        };


        self.init();
        $scope.submit = self.submit;
    });
