'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentAddCtrl
 * @description
 * # StudentAddCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('StudentAddCtrl', function($scope, $http) {
        var self = this;
        self.init = function() {
            $scope.data = {
                name: '',
                phoneNumber: '',
                contributionCoefficient: ''
            };
        };

        self.submit = function() {
            var url = '/student/';
            $http.post(url, $scope.data)
                .then(function success() {
                	console.log('增加成功');
                }, function error() {
                	console.log('增加失败');
                });
        };
        self.init();
        $scope.uniqueUrl = '/student/nameExist';
        $scope.uniqueName = 'name';
        $scope.submit = self.submit;
    });
