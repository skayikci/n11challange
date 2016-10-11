/**
 * Created by serhat on 10/4/16.
 */
(function() {
    'use strict';

    angular
        .module('conferenceApp')
        .config(stateConfig);

    function stateConfig($stateProvider) {
        $stateProvider.state('app', {
            views: {
                'content@': {
                    template: '<h1>Welcome to agenda app!</h1>'
                }
            }
        })
    }
})(angular);