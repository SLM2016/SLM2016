app.controller("Example1Controller",['$scope', '$state', '$timeout', '$rootScope', 'ExampleService',
	function($scope, $state, $timeout, $rootScope, ExampleService){

        
        var getTest = function() {
            ExampleService.getTest().then(function(result){
                $scope.testBackend = result;
            },function(error) {
                console.log(error)
            });
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
        $scope.testBackend;
        /*==========================
             Methods
        ==========================*/

        $scope.getTest = getTest;

        /*==========================
             init
        ==========================*/

        init();
	}
]);