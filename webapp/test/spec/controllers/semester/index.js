'use strict';

describe('Controller: SemesterIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var SemesterIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SemesterIndexCtrl = $controller('SemesterIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SemesterIndexCtrl.awesomeThings.length).toBe(3);
  });
});
