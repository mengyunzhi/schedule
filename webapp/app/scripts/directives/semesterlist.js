'use strict';

/**
 * @ngdoc directive
 * @name scheduleApp.directive:semesterList
 * @description
 * # semesterList
 */
angular.module('scheduleApp')
    .directive('semesterList', function(semester, courseService) {
        return {
            templateUrl: 'views/directives/semesterList.html',
            restrict: 'E',
            scope: {
                ngModel: '='
            },
            link: function postLink(scope) {
                var self = this;

                self.init = function() {
                    scope.semesters = [];
                    scope.selectSemester = {};
                    self.getAllSemesters();
                };

                /**
                 * 获取所有的学期
                 * @return
                 * chenjie
                 */
                self.getAllSemesters = function() {
                    semester.getAll(function(data) {
                        scope.semesters = data;
                        scope.selectSemester = courseService.currentSemester;
                        scope.$watch('ngModel', self.watchNgModel);
                    });
                };

                /**
                 * @Author   chen_jie
                 * @DateTime 2018-10-25T20:16:06+0800
                 * @param    {}                 newValue [description]
                 */
                self.watchNgModel = function(newValue) {
                    if (newValue && newValue.id) {
                        scope.selectSemester = newValue;
                    }
                    if (angular.equals(newValue, {})) {
                        self.updateModel(scope.selectSemester);
                    }
                };

                self.updateModel = function(semester) {
                    scope.ngModel = semester;
                };

                scope.updateModel = self.updateModel;

                self.init();
            }
        };
    });
