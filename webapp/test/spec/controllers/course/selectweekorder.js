'use strict';

describe('Controller: CourseSelectweekorderCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var CourseSelectweekorderCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CourseSelectweekorderCtrl = $controller('CourseSelectweekorderCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CourseSelectweekorderCtrl.awesomeThings.length).toBe(3);
  });
});
