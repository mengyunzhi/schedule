'use strict';

/**
 * 输出不带括号的数组
 * @author chenjie
 * @ngdoc filter
 * @name scheduleApp.filter:arrayWithoutBrackets
 * @function
 * @description
 * # arrayWithoutBrackets
 * Filter in the scheduleApp.
 */
angular.module('scheduleApp')
  .filter('arrayWithoutBrackets', function () {
    return function (input) {
    	var output = input.join();
      return output;
    };
  });
