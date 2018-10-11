'use strict';

describe('Controller: StudentAddCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var StudentAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StudentAddCtrl = $controller('StudentAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StudentAddCtrl.awesomeThings.length).toBe(3);
  });
});
