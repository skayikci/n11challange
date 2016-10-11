/**
 * Created by serhat on 10/4/16.
 */

(function (angular) {
    'use strict';

    angular
        .module('app.conference')
        .controller('ConferenceListController', ConferenceListController);

    ConferenceListController.$inject = ['ConferenceList', '$uibModal', '$scope'];
    /* @ngInject */
    function ConferenceListController(ConferenceList, $uibModal, $scope) {
        var vm = this;
        vm.model = null;
        vm.isLoading = false;
        vm.rowCollection = [];
        vm.itemsByPage=15;
        vm.columns = [
            'name',
            'duration'
        ];
        vm.interval = [
            {time: 5},
            {time: 30},
            {time: 45},
            {time: 60}
        ];
        vm.columnsConfig=[
            {label:'Name',map:'name'},
            {label:'Duration (mins)',map:'duration'},
            {label:'Action',cellTemplateUrl:'delete.html'}
        ];
        vm.addConference = addConference;
        vm.deleteItem = deleteItem;
        vm.deleteItems = deleteItems;

        activate();

        ////////////////

        function activate() {

            loadAll();
        }

        function loadAll(){
            ConferenceList.findAll().then(onSuccessLoad, onErrorLoad);

            function onSuccessLoad(result) {
                vm.rowCollection = result.data;
            }

            function onErrorLoad() {

            }
        }


        function addConference(){
            ConferenceList.addItem(vm.model).then(onAddSuccess, onAddFail);

            function onAddSuccess(){
                vm.rowCollection.push(vm.model);
                vm.rowCollection = [].concat(vm.rowCollection);
            }

            function onAddFail(){

            }
        }

        function deleteItem(conference){
            ConferenceList.deleteItem(conference.id).then(onDeleteSuccess, onDeleteFail);
            function onDeleteSuccess(){
                var index = vm.rowCollection.indexOf(conference);
                if (index > -1) {
                    vm.rowCollection.splice(index, 1);
                }
            }

            function onDeleteFail(){

            }
        }

        function deleteItems(){
            $scope.header = 'Header';
            $scope.body = 'Header';
            $scope.footer = 'Header';
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/util/ConfirmModalView.html',
                controller: 'ConfirmModalController',
                size: 'sm'
            });

            modalInstance.result.then(function () {
                console.log('delete em all');
                ConferenceList.deleteAll().then(onSuccess, onFail);
                function onSuccess(){
                    vm.rowCollection = [];
                }
                function onFail(){

                }
            }, function () {
                console.log('modal dismissed');
            });
        }
    }

})(angular);