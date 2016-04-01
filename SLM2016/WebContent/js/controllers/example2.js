app.controller("Example2Controller",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope){


        var test = function() {
            console.log("Click Test");
            $scope.testBind = "testSuccess"
        }

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