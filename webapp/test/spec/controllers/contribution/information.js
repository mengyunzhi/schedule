'use strict';

describe('Controller: ContributionInformationCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var ContributionInformationCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ContributionInformationCtrl = $controller('ContributionInformationCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ContributionInformationCtrl.awesomeThings.length).toBe(3);
  });
});
