'use strict';

/**
 * @ngdoc filter
 * @name scheduleApp.filter:operateFilter
 * @function
 * @description
 * # operateFilter
 * 学生管理 操作
 */
angular.module('scheduleApp')
    .filter('operateFilter', function() {
    	
    	/**
    	* input   true  false
    	* output  冻结   解冻
    	*/

        return function(input) {
            var output = '';
            if (input) {
                output = '冻结';
            } else {
                output = '解冻';
            }
            return output;
        };
    });
