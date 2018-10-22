'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:CourseIndexCtrl
 * @description
 * # CourseIndexCtrl
 * Controller of the scheduleApp
 * @author     chenjie
 */
angular.module('scheduleApp')
    .controller('CourseIndexCtrl', function($http, $scope, courseService, semester, $filter) {
        var self = this;

        // 初始化
        self.init = function() {
            self.selectAllOrNot = true;
            $scope.params = { page: 0, size: 3 };
            self.load();
        };

        // 加载数据
        self.load = self.reload = function() {
            courseService.page($scope.params, function(data) {
                $scope.data = data;
                // 将所有课程状态初始化为false
                angular.forEach($scope.data.content, function(_list) {
                    _list._checked = false;
                });

                // 获取当前学期
                semester.getAll(function(data) {
                    var currentSemester = $filter('filter')(data, true, status);
                    $scope.currentSemester = currentSemester[0];    
                });
            });

        };

        // 分页时重新加载数据
        self.reloadByPage = function(page) {
            $scope.params.page = page;
            self.reload();
        };

        // 进行每页大小
        self.reloadBySize = function(size) {
            $scope.params.size = size;
            self.load();
        };

        //判断当前是否全选
        self.select = function() {
            if (self.selectAllOrNot) {
                self.selectAll();
            } else {
                self.removeAll();
            }
            self.selectAllOrNot = !self.selectAllOrNot;
        };

        //全选
        self.selectAll = function() {
            angular.forEach($scope.data.content, function(_list) {
                _list._checked = true;
            });

        };

        //全不选
        self.removeAll = function() {
            angular.forEach($scope.data.content, function(_list) {
                _list._checked = false;
            });
        };

        // 批量删除
        self.deleteMultiple = function() {
            var deleteList = $scope.data.content.filter(function(_list) {
                return _list._checked;
            });

            angular.forEach(deleteList, function(deleteIems, key) {
                delete deleteIems._checked;
            });
            courseService.deleteMultiple(deleteList, function() {
                self.reload();
            })
        };

        self.init();
        $scope.selectAll = self.selectAll;
        $scope.select = self.select;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        $scope.deleteMultiple = self.deleteMultiple;


    });
