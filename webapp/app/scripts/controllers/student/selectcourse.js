'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentSelectcourseCtrl
 * @description
 * # StudentSelectcourseCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
  .controller('StudentSelectcourseCtrl', function ($http, $scope, courseService) {
      var self = this;

        // 初始化
        self.init = function() {
            $scope.params = { page: 0, size: 3 };
            self.load();
        };

        // 加载数据
        self.load = self.reload = function() {
            courseService.page($scope.params, function(data) {
                $scope.data = data;
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

        //全选             
        self.selectAll = function(allStatus) {
            $scope.array = courseService.dataIds(allStatus, $scope.data);
        };
     
        self.init();
        $scope.allStatus = false;
        $scope.selectAll = self.selectAll;
        $scope.array = [];
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;        
    });