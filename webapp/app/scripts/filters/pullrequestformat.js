'use strict';

/**
 * @ngdoc filter
 * @name scheduleApp.filter:pullRequestFormat
 * @function
 * @description
 * # pullRequestFormat
 * Filter in the scheduleApp.
 */
angular.module('scheduleApp')
    .filter('pullRequestFormat', function ($scope) {
        return function (input) {
            /**
             *
             * @author liyiheng
             * @date 2018/10/17
             * @description:
             * 将收到的pullRequest转化为指定格式
             */
            var test  = 'qweqw#123 www.coojf.com';
            var titleAndOther = test.split('#');                 // pull request 对应的仓库
            var title = titleAndOther[0];
            var numberAndUrl = titleAndOther[1].split(' ');       // pull request 对应的编号和网址
            var number = numberAndUrl[0];
            var url = numberAndUrl[1];
            return title + '<a href="' + url + '>#' + number + '</a>';
        };
    });
