/**
 * Created by serhat on 10/4/16.
 */


(function (angular) {
    'use strict';

    angular
        .module('app.conference')
        .factory('ConferenceList', ConferenceListFactory);

    ConferenceListFactory.$inject = ['$http'];
    /* @ngInject */
    function ConferenceListFactory($http) {
        return angular.extend(
            {},
            {
                addItem: function (conference) {
                    return $http.post(
                        'api/conference/create',
                        conference,
                        {}
                    ).then(
                        function (result) {
                            return result.data;
                        }
                    );
                },
                findAll: function () {
                    return $http.get('api/conference/find-all');
                },
                deleteItem: function (id) {
                    return $http.delete('api/conference/delete/' + id);
                },
                deleteAll: function(){
                    return $http.delete('api/conference');
                }
            });
    }

})(angular);