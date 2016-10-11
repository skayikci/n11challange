/**
 * Created by serhat on 10/7/16.
 */

(function (angular) {
    'use strict';

    angular
        .module('app.agenda')
        .config(stateConfig);

    function stateConfig($stateProvider) {
        $stateProvider.state({
            name: 'app.agenda',
            url: '/agenda',
            views: {
                'content@': {
                    templateUrl: 'app/agenda/AgendaView.html',
                    controller: 'AgendaController',
                    controllerAs: 'vm'
                }
            },
            data : { pageTitle: 'Agenda' }
        });
    }
})(angular);