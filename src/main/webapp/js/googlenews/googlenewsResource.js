'use strict';

angular.module('itrade').factory('googlenewsResource', function ($http) {

    var googlenews = function(request) {
        return $http.get('rest/googlenews/rss');
    };

    return {
        googlenews: googlenews
    };
});
