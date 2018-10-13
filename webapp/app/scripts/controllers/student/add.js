'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentAddCtrl
 * @description
 * # StudentAddCtrl
 * 学生管理 增加
 */
angular.module('scheduleApp')
    .controller('StudentAddCtrl', function($scope, $http, $state) {
        var self = this;
        self.init = function() {
            //初始化
            $scope.data = {
                name: '',
                phoneNumber: '',
                github: '',
                contributionCoefficient: 1
            };
        };
        //增加数据
        self.submit = function() {
            var url = '/student/';
            $http.post(url, $scope.data)
                .then(function success() {
                    $state.transitionTo('student', {}, { reload: true });
                    console.log('增加成功');
                }, function error() {
                    console.log('增加失败');
                });
        };

        self.init();
        $scope.submit = self.submit;
    });
