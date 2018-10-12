'use strict';

/**
 * @ngdoc directive
 * @name scheduleApp.directive:yunzhiSize
 * @description
 * # 每页大小
 * @Author panjie
 */
angular.module('scheduleApp')
    .directive('yunzhiSize', function () {
        return {
            scope: {
                ngModel: '=',
                reload: '='     // 触发加载的函数
            },
            templateUrl: 'views/directive/yunzhiSize.html',
            restrict: 'E'
        };
    });
