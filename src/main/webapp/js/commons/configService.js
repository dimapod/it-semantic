'use strict';

angular.module('ecom.config',[]);
angular.module('ecom.config').value('localStorage', window.localStorage);
angular.module('ecom.config').factory('config', function ($rootScope, localStorage) {

    var LOCAL_STORAGE_ID = 'configBatchService',
        confString = localStorage[LOCAL_STORAGE_ID];


    var defaultConf = {
        pageSize: 25,
        layout: {
            moreSearchCriteria: false,
            moreNewCriteria: false
        }
    };

    var configuration = confString ? JSON.parse(confString) : defaultConf;
    configuration = angular.extend({}, defaultConf, configuration);

    $rootScope.$watch(function () {
        return configuration;
    }, function () {
        localStorage[LOCAL_STORAGE_ID] = JSON.stringify(configuration);
    }, true);

    return configuration;
});