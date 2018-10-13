'use strict';

describe('Service: contribution', function () {

  // load the service's module
  beforeEach(module('scheduleApp'));

  // instantiate service
  var contribution;
  beforeEach(inject(function (_contribution_) {
    contribution = _contribution_;
  }));

  it('should do something', function () {
    expect(!!contribution).toBe(true);
  });

});
