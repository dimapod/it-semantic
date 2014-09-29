'use strict';

angular.module('itrade').controller('googlenewsCtrl', function ($scope, googlenewsResource) {

    $scope.submitEntitiesRequest = function() {
        googlenewsResource.googlenews().then(function(response) {
//            if (response && response.data && response.data.message) {
//                $scope.entitiesResult = response.data.message;
//            } else {
//                throw new Error('Result is null...');
//            }
        });
    };

});
