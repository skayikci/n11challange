/**
 * Created by serhat on 10/4/16.
 */

(function (angular) {
    'use strict';

    angular
        .module('app.conference')
        .controller('ConferenceListController', ConferenceListController);

    ConferenceListController.$inject = ['ConferenceList', 'ConfirmModal'];
    /* @ngInject */
    function ConferenceListController(ConferenceList, ConfirmModal) {
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
        vm.deleteAll = deleteAll;

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

        function deleteAll(){
            var modalOptions = {
                closeButtonText: 'Cancel',
                actionButtonText: 'Delete',
                headerText: 'Delete  All Items?',
                bodyText: 'Are you sure you want to delete all items?'
            };

            ConfirmModal.showModal({}, modalOptions).then(function (result) {
                console.log(result);
                //ConferenceList.deleteAll().then();
            });
        }
    }

})(angular);