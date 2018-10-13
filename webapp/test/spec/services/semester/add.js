'use strict';

describe('Service: semester/add', function () {

  // load the service's module
  beforeEach(module('scheduleApp'));

  // instantiate service
  var semester/add;
  beforeEach(inject(function (_semester/add_) {
    semester/add = _semester/add_;
  }));

  it('should do something', function () {
    expect(!!semester/add).toBe(true);
  });

});
