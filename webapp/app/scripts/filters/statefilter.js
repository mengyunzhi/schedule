'use strict';

/**
 * @ngdoc filter
 * @name scheduleApp.filter:stateFilter
 * @function
 * @description
 * # stateFilter
 * 学生管理 改变状态
 */
angular.module('scheduleApp')
    .filter('stateFilter', function() {
    	
    	/**
    	* input   true  false
    	* output  正常   冻结 
    	*/

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
