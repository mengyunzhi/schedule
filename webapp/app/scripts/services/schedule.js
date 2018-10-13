'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.schedule
 * @to finsh about schedule service
 * # schedule
 * Service in the scheduleApp.
 */
angular.module('scheduleApp')
    .service('schedule', function($http) {

        var self = this;
        var baseUrl = '/schedule/';		//background controller request address 

        /**
         * get the current term and execute callBack
         * @param  {function} callBack 
         */
        self.getCurrentSemester = function(callBack) {
        	$http.get('/semester/currentSemester')
        	.then(function(response) {
        		callBack(response.data);
        	}, function(response) {
        		console.log(response);
        	});
        };

        /**
         * return a 7*5 array due to week 7 node 5
         * @return {array} 		an array of 7(week) * 5(node)
         */
        self.initSechedules = function() {
        	var schedules = [];
        	for (var week = 0; week < 7; week++) {
        		var nodes = [];
        		for (var node = 0; node < 5; node++) {
        			nodes[node] = null;
        		}
        		schedules[week] = nodes;
        	}
        	return schedules;
        }

        /**
         * get the end time of the start of the distance
         * @param  {string long}	startTime 	start timestamp
         * @param  {string long}   	endTime   	end timestamp
         * @return {int}						weekOrder
         */
        self.getWeekOder = function(startTime, endTime = new Date().getTime()) {
        	var aWeekStamp = 7 * 24 * 60 * 60 * 1000;
        	var startDate = new Date(parseInt(startTime));
        	var endDate = new Date(parseInt(endTime));
        	var interval = endDate.getTime() - startDate.getTime();
        	var weekOrder = parseInt(interval/aWeekStamp) + 1;
        	return weekOrder;
        };

        /**
         * get the current schedule according to the semester and week, and 
         * encapsulate it into an array of 7 * 5
         * @param  {long} semesterId    the semester
         * @param  {int } weekOrder  	the weekOrder
         * @param  {funtion} callBack   operations performed when data are obtained
         * @return {array}            an array of 7(week) * 5(node)
         */		
        self.getnowschedules = function(semesterId, weekOrder, callBack) {
        	var getUrl = baseUrl + 'getnowschedule/' + semesterId + '/' + weekOrder;
        	$http.get(getUrl)
        	.then(function(response) {
        		var data = response.data;
        		if (data.length != 0) {
        			callBack(self.createSechedules(data));
        		} else {
        			callBack(self.initSechedules());
        		}
        	}, function() {
        		console.log('getnowschedule');
        	});
        };

        /**
         * Encapsulate arr as an array 7(week) * 5(node) and return it
         * @param  {arr} arr 	an array of 35 length
         * @return {arr}     	an array 7 * 5
         */
        self.createSechedules = function(arr) {
        	var schedules = self.initSechedules();
        	if (arr.length == 35) {      	
	        	var sign = 0;
	        	var week = 0;
	        	var tempArr = [];
	        	angular.forEach(arr, function(value){
	        		tempArr.push(value);
	        		sign ++;
	        		if (sign % 5 == 0) {
	        			schedules[week] = tempArr;
	        			tempArr = [];
	        			week ++;
	        		}
	        	});
        	}
        	return schedules;
        };
    });