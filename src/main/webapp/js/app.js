'use strict';

angular.module('itrade', [
    'ui.router',
    'notifications',
    'ecom.exceptionHandler',
    'ecom.httpRequestTracker'
])

    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/googlenews");
        $stateProvider
            .state('googlenews', {
                url: "/googlenews",
                templateUrl: "js/googlenews/googlenews.tpl.html"
            });

    });



