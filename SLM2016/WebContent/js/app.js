var STATES = {
    HOME: "home",
    CERTIFICATION: "certification",
    SENDMAIL: "sendMail",
    INVOICE: "invoice",
    STUDENTINFO: "studentInfo",
    STUDENT_INFO_IMPORT: "studentInfo.Import",
    STUDENT_INFO_MANAGE: "studentInfo.Manage"
}

var app = angular.module('app', [
    'ui.router',
    'ct.ui.router.extras',
    'ngScrollbar',
    'ngFileUpload',
    'ui.bootstrap'
])

.config(['$sceProvider', '$stateProvider', '$urlRouterProvider', '$locationProvider', '$animateProvider', '$stickyStateProvider',
	function($sceProvider, $stateProvider, $urlRouterProvider, $locationProvider, $animateProvider, $stickyStateProvider) {


		// ng-bind-html word
        $sceProvider.enabled(false);

        // Start Page
        $urlRouterProvider.otherwise("/");

        $stickyStateProvider.enableDebug(false);

        // ui view setting
        $stateProvider

        .state(STATES.HOME, {
            url: "/",
            views: {
                'home@': {
                    templateUrl: "templates/home.html",
                    controller: 'HomeController',
                }
            }
        })
        .state(STATES.CERTIFICATION, {
            url: "/certification",
            views: {
                'certification@': {
                    templateUrl: "templates/certificationPage.html",
                    controller: 'CertificationController',
                }
            }
        })
        .state(STATES.SENDMAIL, {
            url: "/sendMail",
            views: {
                'sendMail@': {
                    templateUrl: "templates/mailSendingPage.html",
                    controller: 'MailSendingController',
                }
            }
        })

	    .state(STATES.INVOICE, {
            url: "/invoice",
            views: {
                'invoice@': {
                    templateUrl: "templates/invoice.html",
                    controller: 'InvoiceController',
                }
            }
        })
        
        .state(STATES.STUDENTINFO, {
            url: "/studentInfo",
            views: {
                'studentInfo@': {
                    templateUrl: "templates/studentInfo.html"
                }
            }
        })

            .state(STATES.STUDENT_INFO_IMPORT, {
                url: "/import",
                views: {
                    'content@studentInfo': {
                        templateUrl: "templates/studentImport.html",
                        controller: 'StudentImportController',
                    }
                }
            })

            .state(STATES.STUDENT_INFO_MANAGE, {
                url: "/manage",
                views: {
                    'content@studentInfo': {
                        templateUrl: "templates/studentManage.html",
                        controller: 'StudentManageController',
                    }
                }
            })
	}
])

.controller("RootController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope){

        var isHomeView = function() {
            return $state.includes(STATES.HOME);
        }

        var isCertificationView = function() {
            return $state.includes(STATES.CERTIFICATION);
        }
        
        var isSendMailView = function() {
            return $state.includes(STATES.SENDMAIL);
        }

        var isInvoiceView = function() {
            return $state.includes(STATES.INVOICE);
        }

        var studentInfoView = function() {
            return $state.includes(STATES.STUDENTINFO);
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

        $scope.isHomeView = isHomeView;
        $scope.isCertificationView = isCertificationView;
        $scope.isSendMailView = isSendMailView;
        $scope.isInvoiceView = isInvoiceView;
        $scope.studentInfoView = studentInfoView;

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