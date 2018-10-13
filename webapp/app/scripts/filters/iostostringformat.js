'use strict';

/**
 * @ngdoc filter
 * @name scheduleApp.filter:iosToStringFormat
 * @function
 * @description
 * # iosToStringFormat
 * Filter in the scheduleApp.
 */
angular.module('scheduleApp')
  .filter('iosToStringFormat', function () {
    return function (input) {
    	var output = new Date(input);
      return output.getTime();
    };
  });
