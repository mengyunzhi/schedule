'use strict';

/**
 * 判断ng-model 的值是否与后台数据重复
 *若重复增加错误unique
 *scope 参数
 *url 后台判断地址 要求存在返回true 不存在返回false
 *uniquename 后台属性名称
 *outside 
 *unique (可选)当添加此属性时，当视图的值与outside相同时，将不进行验证并直接通过
 */
angular.module('scheduleApp')
    .directive('unique', function($http, $q) {
        return {
        	scope: {
        		url: '=url',
        		name: '=uniqueName',
                outside: '=outside'
        	},
            require: 'ngModel',
            link: function postLink(scope, element, attrs, ctrl) {
            	ctrl.$asyncValidators.unique = function(modelValue, viewValue) {
            		var url = scope.url;
            		var name = scope.name;
            		var param = {};
            		param[name] = viewValue;
            		var d = $q.defer();
                    if (scope.outside != undefined && scope.outside == viewValue) {
                        d.resolve();
                        return d.promise;
                    }
            		$http.get(url, {params: param})
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
