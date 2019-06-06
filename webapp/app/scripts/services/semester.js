'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.semester
 * @description
 * # semester
 * Service in the scheduleApp.
 */
angular.module('scheduleApp')
  .service('semester', function ($http, $filter) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    var self = this;
    var baseUrl = '/semester';
    self.add = function(semester, callBack, failBack) {
    	var addSemester = {};
    	addSemester.name = semester.name;
    	addSemester.startTime = $filter('iosToStringFormat')(semester.startTime);
    	addSemester.endTime = $filter('iosToStringFormat')(semester.endTime);
  		$http.post(baseUrl + '/', addSemester)
  		.then(function() {
  			callBack();
  		}, function(response) {
  			console.log(response);
  			failBack();
  		});
    };
    self.getAll = function(callBack) {
    	$http.get(baseUrl + '/')
    	.then(function(response) {
    		callBack(response.data);
    	}, function() {
    		console.log('fail to get all semester');
    	});
    };
    self.delete = function(semester, callBack) {
    	$http.delete(baseUrl + '/' + semester.id)
    	.then(function() {
    		callBack();
    	}, function() {
            self.alertWindow('删除失败');
            console.log('false to delete semester');
    	});
    };
    self.active = function(id, callBack) {
    	$http.put(baseUrl + '/active/' + id)
    	.then(function() {
    		callBack();
    	}, function() {
            self.alertWindow('操作失败');
    		console.log('fail to active semester');
    	});
    };
    self.getById = function(id, callBack) {
    	$http.get(baseUrl + '/' + id)
    	.then(function(response) {
    		var data = response.data;
    		callBack(data);
    	}, function() {
            self.alertWindow('获取数据失败');
    		console.log('false to get one semester');
    	});
    };
    self.update = function(id, semester, callBack) {
    	var addSemester = {};
    	addSemester.name = semester.name;
    	addSemester.startTime = $filter('iosToStringFormat')(semester.startTime);
    	addSemester.endTime = $filter('iosToStringFormat')(semester.endTime);
    	addSemester.status = semester.status;
    	$http.put(baseUrl + '/' + id, addSemester)
    	.then(function() {
    		callBack();
    	}, function() {
            self.alertWindow('更新失败');
    		console.log('false to update semester');
    	});
    };
    self.findByName = function(name, callBack) {
    	$http.get(baseUrl + '/name/' + name)
    	.then(function(response) {
    		callBack(response.data);
    	}, function() {
            self.alertWindow();
    		console.log('false to find semester by name');
    	});
    };

    /**
     * 将学期时间戳转换成date.tostring格式
     * @param  {object} semester 学期对象
     */
    self.changeSemesterTimeStampFormat = function(semester) {
        semester.startTime = new Date(parseInt(semester.startTime));
        semester.endTime = new Date(parseInt(semester.endTime));
    };

    self.getPage = function(pageParams, callBack) {
        var getPageUrl = baseUrl + '/page/' + pageParams.page + '/' + pageParams.size;
        $http.get(getPageUrl)
        .then(function(response) {
            callBack(response.data);
        }, function() {
            console.log('fail to get semster page');            
        });
    };

    self.getPageAndName = function(pageParams, callBack) {
        var getPageAndNameUrl = baseUrl + '/pageByName/' + pageParams.name + '/' 
        + pageParams.page + '/' + pageParams.size;
        $http.get(getPageAndNameUrl)
        .then(function(response) {
            callBack(response.data);
        }, function() {
            console.log('fail to pageAndName semster');
        });
    };

      // 弹窗， 写一个方法，方便重写
      self.alertWindow = function (msg) {
          alert(msg);
      };
  });
