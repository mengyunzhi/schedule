'use strict';

describe('Controller: CourseSelectscheduleCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var CourseSelectscheduleCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CourseSelectscheduleCtrl = $controller('CourseSelectscheduleCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CourseSelectscheduleCtrl.awesomeThings.length).toBe(3);
  });
});
