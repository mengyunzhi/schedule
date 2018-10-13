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
    .controller('CourseIndexCtrl', function($timeout, $http, $scope, course) {
        var self = this;

        // 初始化
        self.init = function() {
            $scope.params = { page: 0, size: 3 };
            self.load();
        };

        // 加载数据
        self.load = self.reload = function() {
            course.page($scope.params, function(data) {
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

        /**
         * 删除
         * @param  {[type]} object 要删除的对象
         * @return {}
         * 
         */
        self.delete = function(object) {
            // 应该去触发后台的删除操作
            course.delete(object, function() {
                // 将元素隐藏掉
                object._delete = true;
            });
        };
        
       
        
        self.selectAll = function(all) {
            console.log($scope.data);
            if (all === true) {
                var j = 0;
                for (var i = $scope.data.size - 1; i >= 0; i--,j++) {
                    $scope.array[j] = $scope.data.content.id[i]
                }

            } else {
                $scope.array.splice(0, $scope.array.length);
            }
        };

        //点击 删除所选 按钮的事件
        self.deleteMultiple = function() {
            var url = '/Course/deleteAllById';    
            $http.delete(url,{params: {ids: $scope.array}}).then(function success() {
                console.log("deletesuccesss");
            },function error() {
                console.log("deleteerror");
            });
        };
        
        self.init();
        $scope.all = false;
        $scope.selectAll = self.selectAll;
        $scope.array = [];
        $scope.delete = self.delete;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        $scope.deleteMultiple = self.deleteMultiple;
        

    });
