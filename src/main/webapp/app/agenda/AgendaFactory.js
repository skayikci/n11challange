/**
 * Created by serhat on 10/7/16.
 */

(function (angular) {
    'use strict';

    angular
        .module('app.agenda')
        .factory('Agenda', AgendaFactory);

    AgendaFactory.$inject = ['$http'];
    /* @ngInject */
    function AgendaFactory($http) {
        return angular.extend(
            {},
            {
                createAgenda: function () {
                    return $http.get('api/agenda');
                }
            });
    }

})(angular);