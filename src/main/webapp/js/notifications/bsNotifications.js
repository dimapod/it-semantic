angular.module('notifications', ['template/notifications/notifications.tpl.html']);
angular.module('notifications')
    .directive('bsNotifications', function (notifications) {
        return {
            restrict: 'A',
            scope: {
                ngModel: '='
            },
            templateUrl: 'template/notifications/notifications.tpl.html',
            transclude: false,
            replace: true,

            link: function (scope, element, attrs) {

            },

            controller: function($scope) {
                $scope.notifications = notifications;
                $scope.removeNotification = function (notification) {
                    notifications.remove(notification);
                };
            }
        };
    });

angular.module("template/notifications/notifications.tpl.html", []).run(["$templateCache", function($templateCache) {
    $templateCache.put("template/notifications/notifications.tpl.html",
        "<div class='notification-container' ng-show='notifications.getCurrent().length'>" +
            "<div ng-class=\"['alert', 'alert-'+notification.type]\" ng-repeat=\"notification in notifications.getCurrent()\">" +
                "<button class='close' ng-click='removeNotification(notification)'>x</button>" +
                "{{notification.message}}" +
            "</div>" +
        "</div>"
    );
}]);

angular.module('notifications')
    .factory('notifications', ['$rootScope', '$timeout', function ($rootScope, $timeout) {

    var notifications = {
        'STICKY': [],
        'ROUTE_CURRENT': [],
        'ROUTE_NEXT': []
    };
    var notificationsService = {};

    var addNotification = function (notificationsArray, notificationObj) {
        if (!angular.isObject(notificationObj)) {
            throw new Error("Only object can be added to the notification service");
        }
        notificationsArray.push(notificationObj);

        $timeout(function() {
            notificationsService.remove(notificationObj);
        }, 8000);

        return notificationObj;
    };

    $rootScope.$on('$routeChangeSuccess', function () {
        notifications.ROUTE_CURRENT.length = 0;

        notifications.ROUTE_CURRENT = angular.copy(notifications.ROUTE_NEXT);
        notifications.ROUTE_NEXT.length = 0;
    });

    notificationsService.getCurrent = function () {
        return [].concat(notifications.STICKY, notifications.ROUTE_CURRENT);
    };

    notificationsService.pushSticky = function (message, type) {
        return addNotification(notifications.STICKY, { message: message, type: type });
    };

    notificationsService.pushForCurrentRoute = function (message, type) {
        return addNotification(notifications.ROUTE_CURRENT, { message: message, type: type });
    };

    notificationsService.pushForNextRoute = function (message, type) {
        return addNotification(notifications.ROUTE_NEXT, { message: message, type: type });
    };

    notificationsService.remove = function (notification) {
        angular.forEach(notifications, function (notificationsByType) {
            var idx = notificationsByType.indexOf(notification);
            if (idx > -1) {
                notificationsByType.splice(idx, 1);
            }
        });
    };

    notificationsService.removeAll = function () {
        angular.forEach(notifications, function (notificationsByType) {
            notificationsByType.length = 0;
        });
    };

    return notificationsService;
}]);



