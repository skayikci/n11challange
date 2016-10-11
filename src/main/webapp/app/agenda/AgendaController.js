/**
 * Created by serhat on 10/7/16.
 */

(function (angular) {
    'use strict';

    angular
        .module('app.agenda')
        .controller('AgendaController', AgendaController);

    AgendaController.$inject = ['Agenda'];
    /* @ngInject */
    function AgendaController(Agenda) {
        var vm = this;

        vm.data = [];

        activate();

        ////////////////

        function activate() {
            loadAll();
        }

        function loadAll(){
            Agenda.createAgenda().then(onAgendaSuccess, onAgendaFail);
            function onAgendaSuccess(result){
                vm.data = result.data;
            }

            function onAgendaFail(){

            }
        }


    }

})(angular);