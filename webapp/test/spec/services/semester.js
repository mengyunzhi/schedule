'use strict';

describe('Service: semester', function () {

  // load the service's module
  beforeEach(module('scheduleApp'));

  // instantiate service
  var semester;
  beforeEach(inject(function (_semester_) {
    semester = _semester_;
  }));

  it('should do something', function () {
    expect(!!semester).toBe(true);
  });

});
