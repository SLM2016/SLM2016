app.controller("HomeController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope){


        var init = function() {
        }


        /*==========================
            Events
        ==========================*/

        /*==========================
            Members
        ==========================*/

        $scope.testBind = ""
        /*==========================
             Methods
        ==========================*/

        $scope.test = test;

        /*==========================
             init
        ==========================*/

        init();

	}
]);