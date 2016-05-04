/*================================
 *  Nav-sidebar
 *  @author: Tim
 *  @time 2016 / 04 / 01
 * ================================*/

app.directive('navSidebar', ['$rootScope',
    function($rootScope) {
        return {
            restrict: "E",
            scope: true,
            templateUrl: "templates/directives/nav-sidebar.html",
            link: function(scope, element, attrs) {},
            controller: ['$scope', '$state', '$timeout',
                function($scope, $state, $timeout) {

                    var init = function() {}

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

                    var isStudentInfoView = function() {
                        return $state.includes(STATES.STUDENTINFO);
                    }

                    var isStudentInfoManageView = function() {
                        return $state.includes(STATES.STUDENT_INFO_MANAGE);
                    }

                    var isStudentInfoImportView = function() {
                        return $state.includes(STATES.STUDENT_INFO_IMPORT);
                    }

                    var toggleStudentMenu = function() {
                        $scope.isShowStudentMenu = !$scope.isShowStudentMenu;
                    }
                    /*==========================
                        Events
                    ==========================*/

                    $scope.$on("$stateChangeSuccess", function(event, toState, toParams, fromState, fromParams) {
                        if($state.includes(STATES.STUDENTINFO)) {
                            $scope.isShowStudentMenu = true;
                        }
                    });

                    /*==========================
                        Members
                    ==========================*/

                    $scope.isShowStudentMenu = false;

                    /*==========================
                        Methods
                    ==========================*/
                    $scope.isHomeView = isHomeView;

                    $scope.isCertificationView = isCertificationView;
                    $scope.isSendMailView = isSendMailView;                    
                    $scope.isInvoiceView = isInvoiceView;
                    $scope.isStudentInfoView = isStudentInfoView;    

                    $scope.isStudentInfoManageView = isStudentInfoManageView;   
                    $scope.isStudentInfoImportView = isStudentInfoImportView;

                    $scope.toggleStudentMenu = toggleStudentMenu;             
                    /*==========================
                        init
                    ==========================*/

                    init();
                }
            ]
        }
    }
]);
