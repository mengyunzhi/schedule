'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ScheduleIndexCtrl
 * @description
 * # ScheduleIndexCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
	.controller('SystemSettingIndexCtrl', function($scope, systemSetting) {

		var self = this;

		//初始化
		self.init = function() {
			systemSetting.getAll(function(settings) {
				$scope.settings = settings;
			});
		}

		//更新
		self.update = function() {
			systemSetting.updateSettings($scope.settings, function() {
				alert('设置成功');
			}, function() {
				alert('设置失败');
			});
		};

		self.init();
		$scope.update = self.update;

	});
