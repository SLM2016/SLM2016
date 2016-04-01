var STATES = {
    EXAMPLE1: "example1",
    EXAMPLE2: "example2"
}

var app = angular.module('app', [
	'ui.router',
	'ct.ui.router.extras',
    'ngScrollbar'
])

.config(['$sceProvider', '$stateProvider', '$urlRouterProvider', '$locationProvider', '$animateProvider', '$stickyStateProvider',
	function($sceProvider, $stateProvider, $urlRouterProvider, $locationProvider, $animateProvider, $stickyStateProvider) {


		// ng-bind-html word
        $sceProvider.enabled(false);

        // Start Page
        $urlRouterProvider.otherwise("/example1");

        $stickyStateProvider.enableDebug(false);

        // ui view setting
        $stateProvider

        .state(STATES.EXAMPLE1, {
            url: "/example1",
            views: {
                'example1@': {
                    templateUrl: "templates/example1.html",
                    controller: 'Example1Controller',
                }
            }
        })
        .state(STATES.EXAMPLE2, {
            url: "/example2",
            views: {
                'example2@': {
                    templateUrl: "templates/example2.html",
                    controller: 'Example2Controller',
                }
            }
        })
	}
])

.controller("RootController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope){

        var isExample1View = function() {
            
            return $state.includes(STATES.EXAMPLE1);
        }

        var isExample2View = function() {
            return $state.includes(STATES.EXAMPLE2);
        }

        var init = function() {
            
        }


        /*==========================
            Events
        ==========================*/

        /*==========================
            Members
        ==========================*/
        /*==========================
             Methods
        ==========================*/
        $scope.isExample1View = isExample1View;
        $scope.isExample2View = isExample2View;

        /*==========================
             init
        ==========================*/

        init();

	}
]);

app.directive('loading',  ['$timeout', function($timeout){
  return {
        restrict: 'E',
        templateUrl: "templates/directives/loading.html"
    };
}]);
