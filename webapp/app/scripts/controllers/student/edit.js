'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentEditCtrl
 * @description
 * # StudentEditCtrl
 * 学生管理  编辑
 */
angular.module('scheduleApp')
    .controller('StudentEditCtrl', function($scope, $http, $stateParams, $state) {
        var self = this;
        self.init = function() {
            //获取当前编辑的ID
            var id = $stateParams.id;
            //用接收的ID去请求信息
            var url = '/student/' + id;
            $http.get(url)
                .then(function success(response) {
                    $scope.data = response.data;
                }, function error() {
                    console.log('error');
                });
        };

        //提交数据
        self.submit = function() {
            var id = $stateParams.id;
            var url = '/student/' + id;
            $http.put(url, $scope.data)
                .then(function success() {
                    console.log('success');
                }, function error() {
                    console.log('error');
                });
        };

        self.init();
        $scope.submit = self.submit;
    });
