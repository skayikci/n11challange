/**
 * Created by serhat on 10/4/16.
 */

(function (angular) {
    'use strict';

    angular
        .module('conferenceApp', [
            'ui.router',
            'app.conference',
            'app.agenda',
            'app.util'
        ]);
})(angular);