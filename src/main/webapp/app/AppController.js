/**
 * Created by serhat on 10/4/16.
 */
(function (angular) {
    'use strict';

    angular
        .module('conferenceApp')
        .controller('AppController', AppController);

    AppController.$inject = [];
    /* @ngInject */
    function AppController() {
        var vm = this;
    }

})(angular);