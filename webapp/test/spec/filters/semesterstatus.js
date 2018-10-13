'use strict';

describe('Filter: semesterStatus', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var semesterStatus;
  beforeEach(inject(function ($filter) {
    semesterStatus = $filter('semesterStatus');
  }));

  it('should return the input prefixed with "semesterStatus filter:"', function () {
    var text = 'angularjs';
    expect(semesterStatus(text)).toBe('semesterStatus filter: ' + text);
  });

});
