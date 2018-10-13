'use strict';

/**
 * @ngdoc filter
 * @name scheduleApp.filter:timeFormat
 * @function
 * @description
 * # timeFormat
 * Filter in the scheduleApp.
 */
angular.module('scheduleApp')
  .filter('timeFormat', function () {
    return function (input) {
    	var date = new Date(parseInt(input));
    	var ouput = '';
    	var year = date.getFullYear();
    	var month = date.getMonth() + 1;
    	var day = date.getDate();
    	ouput = year + '年' + month + '月' + day + '日';
      return ouput;
    };
  });
