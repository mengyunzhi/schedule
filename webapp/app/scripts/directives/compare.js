'use strict';

/**
 * @ngdoc directive
 * @name scheduleApp.directive:compare
 * @description
 * # compare
 */
angular.module('scheduleApp')
  .directive('compare', function () {
    return {
      require: 'ngModel',
      scope: {
      	compare: '=compare'
      },
      link: function postLink(scope, element, attrs, c) {
      		c.$validators.semester = function(m,v) {
      			if (scope.compare == null) { return true;}
      			var start = new Date(scope.compare);
        		var end = new Date(v);
        		if (start.getTime() > end.getTime()) { 	
      				return false;
      			}
      			return true;	
      		};
      		scope.$watch('compare', function(newValue) {
      			if (newValue) {
      				var start = new Date(newValue);
        			var end = new Date(c.$viewValue);
        			if (start.getTime() > end.getTime()) {
        				c.$setValidity('semester',false);
        			} else{
        				c.$setValidity('semester',true);
        			}
      			}
      		});
      }
    };
  });
