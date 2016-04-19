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

                    var init = function() {

                    }

                    var isExample1View = function() {

                        return $state.includes(STATES.EXAMPLE1);
                    }

                    var isExample2View = function() {
                        return $state.includes(STATES.EXAMPLE2);
                    }

                    var isInvoiceView = function() {
                        return $state.includes(STATES.INVOICE);
                    }

                    var studentInfoView = function() {
                        return $state.includes(STATES.STUDENTINFO);
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
                    $scope.isInvoiceView = isInvoiceView;
                    $scope.studentInfoView = studentInfoView;
                    /*==========================
                        init
                    ==========================*/

                    init();
                }
            ]
        }
    }
]);
