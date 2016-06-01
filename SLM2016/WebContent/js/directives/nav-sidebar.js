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

                    var isCourseInfoView = function() {
                        return $state.includes(STATES.COURSEINFO);
                    }

                    var isCourseInfoManageView = function() {
                        return $state.includes(STATES.COURSEINFO_MANAGE);
                    }

                    var isCourseInfoCreateView = function() {
                        return $state.includes(STATES.COURSEINFO_CREATE);
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

                    var isOthersView = function() {
                        return $state.includes(STATES.OTHERS);
                    }

                    var isOthersCertificationView = function() {
                        return $state.includes(STATES.OTHERS_CERTIFICATION);
                    }
                    
                    var isOthersSendMailView = function() {
                        return $state.includes(STATES.OTHERS_SENDMAIL);
                    }
                    
                    var isOthersInvoiceView = function() {
                        return $state.includes(STATES.OTHERS_INVOICE);
                    }

                    var toggleCourseMenu = function() {
                        $scope.isShowCourseMenu = !$scope.isShowCourseMenu;
                    }

                    var toggleStudentMenu = function() {
                        $scope.isShowStudentMenu = !$scope.isShowStudentMenu;
                    }

                    var toggleOthersMenu = function() {
                        $scope.isShowOthersMenu = !$scope.isShowOthersMenu;
                    }
                    /*==========================
                        Events
                    ==========================*/

                    $scope.$on("$stateChangeSuccess", function(event, toState, toParams, fromState, fromParams) {
                        if($state.includes(STATES.STUDENTINFO)) {
                            $scope.isShowStudentMenu = true;
                        }
                        else if($state.includes(STATES.OTHERS)) {
                            $scope.isShowOthersMenu = true;
                        }
                        else if($state.includes(STATES.COURSEINFO)) {
                            $scope.isShowCourseMenu = true;
                        }
                    });

                    /*==========================
                        Members
                    ==========================*/

                    $scope.isShowCourseMenu = false
                    $scope.isShowStudentMenu = false;
                    $scope.isShowOthersMenu = false;

                    /*==========================
                        Methods
                    ==========================*/
                    $scope.isHomeView = isHomeView;

                    $scope.isCourseInfoView = isCourseInfoView;
                    $scope.isCourseInfoManageView = isCourseInfoManageView;
                    $scope.isCourseInfoCreateView = isCourseInfoCreateView;

                    $scope.isStudentInfoView = isStudentInfoView;    
                    $scope.isStudentInfoManageView = isStudentInfoManageView;   
                    $scope.isStudentInfoImportView = isStudentInfoImportView;


                    $scope.isOthersView = isOthersView;
                    $scope.isOthersCertificationView = isOthersCertificationView;
                    $scope.isOthersSendMailView = isOthersSendMailView;                    
                    $scope.isOthersInvoiceView = isOthersInvoiceView;

                    $scope.toggleCourseMenu = toggleCourseMenu;
                    $scope.toggleStudentMenu = toggleStudentMenu;
                    $scope.toggleOthersMenu = toggleOthersMenu;         
                    /*==========================
                        init
                    ==========================*/

                    init();
                }
            ]
        }
    }
]);
