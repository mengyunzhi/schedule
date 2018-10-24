'use strict';

/**
 * @ngdoc directive
 * @name scheduleApp.directive:semesterList
 * @description
 * # semesterList
 */
angular.module('scheduleApp')
    .directive('semesterList', function(semester) {
        return {
            templateUrl: 'views/directives/semesterList.html',
            restrict: 'E',
            scope: {
                changeObject: '='
            },
            link: function postLink($scope) {
                var self = {};

                self.init = function() {
                    $scope.selectObject = {};

                };

                self.init();
                /**
                 * 获取所有的学期
                 * @return
                 * chenjie
                 */
                self.getAllSemesters = function(scope) {
                    semester.getAll(function(data) {
                        scope.semesters = data;
                    });
                };

                $scope.$watch('selectObject', function(newValue) {
                    if (newValue && newValue.id) {
                        // 改变选中对象
                        scope.changeObject(newValue);
                        $scope.selectedSemester = newValue;
                    }
                });

                self.getAllSemesters($scope);
            }
        };
    });
