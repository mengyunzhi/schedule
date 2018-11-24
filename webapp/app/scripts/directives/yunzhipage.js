'use strict';

/**
 * @ngdoc directive
 * @name scheduleApp.directive:yunzhiPage
 * @description
 * # 分页
 * panjie
 */
angular.module('scheduleApp')
    .directive('yunzhiPage', function() {
        return {
            templateUrl: 'views/directives/yunzhiPage.html',
            restrict: 'E',
            scope: {
                number: '=number',
                totalPages: '=',
                reloadPage: '=',
                first: '=',
                last: '='
            },
            link: function postLink(scope) {
                var self = this;
                self.showPage = 3;
                self.halfShowPage = parseInt(self.showPage / 2);

                self.testPage = function() {
                    if (scope.number && scope.totalPages && scope.number > (scope.totalPages - 1)) {
                        if (scope.totalPages === 0) {
                            if (scope.number !== 0) {
                                scope.reloadPage(0);
                            }
                        } else {
                            scope.reloadPage(scope.totalPages - 1);
                        }
                        return false;
                    }
                    return true;
                };

                self.creatPage = function() {
                    if (self.testPage()) {
                        var totalPages = scope.totalPages;
                        var beginPage = 1;
                        var endPage = totalPages;
                        var showPage = self.showPage;
                        var number = scope.number + 1;
                        var halfShowPage = self.halfShowPage;
                        var pages = [];
                        if (number + halfShowPage > showPage) {
                            if (number + halfShowPage > totalPages) {
                                beginPage = totalPages - showPage + 1;
                                endPage = totalPages;
                            } else {
                                beginPage = number - halfShowPage;
                                endPage = number + halfShowPage;
                            }
                        } else {
                            beginPage = 1;
                            endPage = showPage;
                        }
                        if (showPage > totalPages) {
                            beginPage = 1;
                            endPage = totalPages;
                        }
                        if (totalPages === undefined) {
                            beginPage = 1;
                            endPage = 0;
                        }
                        for (var i = beginPage; i <= endPage; i++) {
                            pages.push(i);
                        }
                        scope.pages = pages;
                    }
                };

                self.reloadByNumber = function(number) {
                    if (number >= 0 && number < scope.totalPages) {
                        scope.reloadPage(number);
                    }
                };

                scope.$watch('number', function() {
                    self.creatPage();
                });

                scope.$watch('totalPages', function() {
                    self.creatPage();
                });

                scope.reloadByNumber = self.reloadByNumber;
            }
        };
    });
