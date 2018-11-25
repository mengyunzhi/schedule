'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:SemesterIndexCtrl
 * @description
 * # SemesterIndexCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('SemesterIndexCtrl', function($scope, $state, semester) {
        var self = this;
        
        self.init = function() {
            $scope.query = {};
            $scope.query.name = '';
            $scope.pageParams = {page: 0, size: 5};
            self.reload();
        };
        self.reload = function() {
            semester.getPage($scope.pageParams, function(data) {
                $scope.page = data;
            });
        };
        /**
         * 根据当前页数获取分页
         * @param  当前页数
         */
        self.reloadByPage = function(page) {
            $scope.pageParams.page = page;
            self.reload();
        };
        self.delete = function(ob) {
            semester.delete(ob, function() {
                self.reload();
            });
        };
        self.active = function(ob) {
            semester.active(ob.id, function() {
                self.reload();
            });
        };
        self.findByName = function() {
            if ($scope.query.name == '') {
                self.reload();
            } else {
                var pageAndNameParams = {page: 0, size: 5};
                pageAndNameParams.name = $scope.query.name;
                semester.getPageAndName(pageAndNameParams, function(data) {
                    $scope.page = data;
                });
            }
        };
        self.init();
        $scope.delete = self.delete;
        $scope.active = self.active;
        $scope.findByName = self.findByName;
        $scope.reloadByPage = self.reloadByPage;
    });
