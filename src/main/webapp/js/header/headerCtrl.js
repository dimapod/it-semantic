angular.module('itrade').controller('headerCtrl',
    function ($rootScope, $scope, $location, httpRequestTracker, $templateCache, $http) {
        $scope.location = $location;
        $scope.isNavbarActive = function (navBarPath) {
            return $location.path() === navBarPath;
        };

        $rootScope.hasPendingRequests = function () {
            return httpRequestTracker.hasPendingRequests();
        };
    });