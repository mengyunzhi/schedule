'use strict';

/**
 * @ngdoc filter
 * @name scheduleApp.filter:semesterStatus
 * @function
 * @description
 * # semesterStatus
 * Filter in the scheduleApp.
 */
angular.module('scheduleApp')
  .filter('semesterStatus', function () {
    return function (input) {
      if (input) {
      	return '已激活';
      } else {
      	return '未激活';
      }
    };
  });
