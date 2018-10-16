'use strict';

/**
 * @ngdoc directive
 * @name scheduleApp.directive:unique
 * @description
 * # unique
 */
angular.module('scheduleApp')
    .directive('unique', function($http, $q) {
        return {
        	scope: {
        		url: '=url',
        		name: '=uniqueName'
        	},
            require: 'ngModel',
            link: function postLink(scope, element, attrs, ctrl) {
            	ctrl.$asyncValidators.unique = function(modelValue, viewValue) {
            		var url = scope.url;
            		var name = scope.name;
            		var d = $q.defer();
            		$http.get(url, {params: {name: viewValue}})
            		.then(function(response) {
            			if (response.data) {
            				d.reject();	
            			} else {
            				d.resolve();
            			}
            		});
            		return d.promise;
            	};
            }
        };
    });
