'use strict';

/**
 * @ngdoc filter
 * @name scheduleApp.filter:stateFilter
 * @function
 * @description
 * # stateFilter
 * 学生管理  状态
 */
angular.module('scheduleApp')
    .filter('stateFilter', function() {
        return function(input) {
            var output = '';
            if (input) {
                output = '正常';
            } else {
                output = '冻结';
            }
            return output;
        };
    });
