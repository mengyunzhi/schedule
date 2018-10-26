'use strict';

/**
 * 将数字过滤为星期
 * input 数字
 * # weekFiltration
 */
angular.module('scheduleApp')
  .filter('weekFiltration', function () {
    return function (input) {
    	var output = '';
    	switch(parseInt(input)) {
    		case 1:output = '星期一' ;break;
    		case 2:output = '星期二' ;break;
    		case 3:output = '星期三' ;break;
    		case 4:output = '星期四' ;break;
    		case 5:output = '星期五' ;break;
    		case 6:output = '星期六' ;break;
    		case 7:output = '星期日' ;break;
    		default: output = '星期一';break;
    	}
    	return output;
    };
  });
