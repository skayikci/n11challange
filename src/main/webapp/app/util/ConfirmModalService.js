/**
 * Created by serhat on 10/11/16.
 */

(function (angular) {
    'use strict';

    angular
        .module('app.util')
        .service('ConfirmModal', ConfirmModalService);

    ConfirmModalService.$inject = ['$uibModal'];
    /* @ngInject */
    function ConfirmModalService($uibModal) {
        var vm = this;

        vm.showModal = showModal;

        vm.modalDefaults = {
            backdrop: true,
            keyboard: true,
            modalFade: true,
            templateUrl: '/app/util/ConfirmModalView.html'
        };

        vm.modalOptions = {
            closeButtonText: 'Close',
            actionButtonText: 'OK',
            headerText: 'Proceed?',
            bodyText: 'Perform this action?'
        };

        ////////////////

        function showModal(modalDefs, modalOpts) {
            if(!modalDefs) modalDefs = {};
            modalDefs.backdrop = 'static';
            return show(modalDefs, modalOpts);
        }

        function show(modalDefs, modalOpts){
            var tempDefs = {};
            var tempOpts = {};

            angular.extend(tempOpts, vm.modalOptions, modalOpts);
            angular.extend(tempDefs, vm.modalDefaults, modalDefs);

            if(!tempDefs.controller){
                tempDefs.controller = function ($scope, $modalInstance){
                    vm.modalOptions = tempOpts;
                    vm.modalOptions.ok = function (result){
                        $modalInstance.close(result);
                    };
                    vm.modalOptions.close = function (result){
                        $modalInstance.dismiss('cancel');
                    }
                }
            }

            return $uibModal.open(tempDefs).result;

        }

    }

})(angular);