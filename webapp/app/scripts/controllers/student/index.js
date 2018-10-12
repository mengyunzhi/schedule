'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentIndexCtrl
 * @description
 * # StudentIndexCtrl
 * 学生管理
 */
angular.module('scheduleApp')
    .controller('StudentIndexCtrl', function($scope, $http) {
        var self = this;
        self.init = function() {
            $scope.change = {
                change: false
            };

            var url = '/student/';
            $http.get(url)
                .then(function success(response) {
                    $scope.student = response.data;
                }, function error() {

                });
        };

        self.changeState = function(change) {
            if (change === false) {
                $scope.change = true;
            } else {
                $scope.change = false;
            }

        };

        self.init()
        $scope.changeState = self.changeState;
    });
