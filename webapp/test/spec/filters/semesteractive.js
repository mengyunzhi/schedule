'use strict';

describe('Filter: semesterActive', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var semesterActive;
  beforeEach(inject(function ($filter) {
    semesterActive = $filter('semesterActive');
  }));

  it('should return the input prefixed with "semesterActive filter:"', function () {
    var text = 'angularjs';
    expect(semesterActive(text)).toBe('semesterActive filter: ' + text);
  });

});
