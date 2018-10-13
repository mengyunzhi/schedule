'use strict';

describe('Controller: ContributionEditCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var ContributionEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ContributionEditCtrl = $controller('ContributionEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ContributionEditCtrl.awesomeThings.length).toBe(3);
  });
});
