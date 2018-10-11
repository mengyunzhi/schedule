'use strict';

/**
 * @ngdoc filter
 * @name scheduleApp.filter:semesterActive
 * @function
 * @description
 * # semesterActive
 * Filter in the scheduleApp.
 */
angular.module('scheduleApp')
  .filter('semesterActive', function () {
    return function (input) {
      if (input) {
      	return '当前学期';
      } else {
      	return '点击激活';
      }
    };
  });
