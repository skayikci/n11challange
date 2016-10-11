/**
 * Created by serhat on 10/11/16.
 */
(function (angular) {
    'use strict';

    angular
        .module('app.util')
        .controller('ConfirmModalController', ConfirmModalController);

    ConfirmModalController.$inject = ['$scope', '$uibModalInstance'];
    /* @ngInject */
    function ConfirmModalController($scope, $uibModalInstance) {
        var vm = this;
        vm.header = 'Confirm';
        vm.body= 'Are you sure you want to delete them all?';

        $scope.ok = function () {
            $uibModalInstance.close();
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }

})(angular);