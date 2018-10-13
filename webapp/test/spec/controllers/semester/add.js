'use strict';

describe('Controller: SemesterAddCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var SemesterAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SemesterAddCtrl = $controller('SemesterAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SemesterAddCtrl.awesomeThings.length).toBe(3);
  });
});
