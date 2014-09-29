angular.module('ecom.httpRequestTracker', []);
angular.module('ecom.httpRequestTracker').factory('httpRequestTracker', ['$http', function ($http) {

    var httpRequestTracker = {};
    httpRequestTracker.hasPendingRequests = function () {

        var hasRequests = false;
        if ($http.pendingRequests.length > 0) {
            angular.forEach($http.pendingRequests, function(req) {
                if (req.url != "rest/console/types") {
                    hasRequests = true;
                }
            });
        }

        return hasRequests;
    };

    return httpRequestTracker;
}]);