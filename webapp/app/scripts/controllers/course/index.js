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
        
        self.init();
        $scope.delete = self.delete;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        // 
        // //数组
        // $scope.courses = [{
        //     id: 1,
        //     name: "大物",
        //     checked: "false"
        // }, {
        //     id: 2,
        //     name: "复变",
        //     checked: "false"
        // }, {
        //     id: 3,
        //     name: "数据结构",

        //     checked: "false"
        // }, {
        //     id: 4,
        //     name: "数电",
        //     checked: "false"
        // }];
        // $scope.all = false;
        // self.selectAll = function(all) {
        //     if (all === true) {
        //         angular.forEach($scope.courses, function(course) {
        //             course.checked = true;
        //         })
        //     } else {
        //         angular.forEach($scope.courses, function(course) {
        //             course.checked = false;
        //         })
        //     }
        // };
        // $scope.selectAll = self.selectAll;

        // //点击 删除所选 按钮的事件
        // $scope.deleteMultiple = function() {

        // };
    });
