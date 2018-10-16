'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentAddCtrl
 * @description
 * # StudentAddCtrl
 * 学生管理  增加
 */
angular.module('scheduleApp')
    .controller('StudentAddCtrl', function($scope, $http, $state) {
        var self = this;
        //初始化
        self.init = function() {
            $scope.data = {
                name: '',
                phoneNumber: '',
                github: '',
                contributionCoefficient: 1
            };
        };

        self.submit = function() {
            var url = '/student/';
            $http.post(url, $scope.data)
                .then(function success() {
                    $state.transitionTo('student', {}, { reload: true });
                }, function error() {
                    console.log('增加失败');
                });
        };
        self.init();
        $scope.uniqueUrl = '/student/nameExist';
        $scope.uniqueName = 'name';
        $scope.submit = self.submit;
    });
