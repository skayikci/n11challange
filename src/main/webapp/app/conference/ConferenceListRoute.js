/**
 * Created by serhat on 10/4/16.
 */

(function (angular) {
    'use strict';

    angular
        .module('app.conference')
        .config(stateConfig);

    function stateConfig($stateProvider) {
        $stateProvider.state({
            name: 'app.conference',
            url: '/add-item',
            views: {
                'content@': {
                    templateUrl: 'app/conference/ConferenceListView.html',
                    controller: 'ConferenceListController',
                    controllerAs: 'vm'
                }
            },
            data : { pageTitle: 'Add Conference' }
        });
    }
})(angular);