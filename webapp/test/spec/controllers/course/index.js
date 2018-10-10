'use strict';

describe('Controller: CourseIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var CourseIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CourseIndexCtrl = $controller('CourseIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CourseIndexCtrl.awesomeThings.length).toBe(3);
  });
});
