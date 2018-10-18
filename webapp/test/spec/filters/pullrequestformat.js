'use strict';

describe('Filter: pullRequestFormat', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var pullRequestFormat;
  beforeEach(inject(function ($filter) {
    pullRequestFormat = $filter('pullRequestFormat');
  }));

  it('should return the input prefixed with "pullRequestFormat filter:"', function () {
    var text = 'angularjs';
    expect(pullRequestFormat(text)).toBe('pullRequestFormat filter: ' + text);
  });

});
