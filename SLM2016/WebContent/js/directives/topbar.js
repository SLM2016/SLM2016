/*================================
 *  Topbar
 *  @author: Tim
 *  @time 2016 / 04 / 01
 * ================================*/

app.directive('topbar', ['$rootScope',
    function($rootScope) {
    return {
        restrict: "E",
        scope: true,
        templateUrl: "templates/directives/topbar.html",
        link: function(scope, element, attrs) {},
        controller: ['$scope', '$state', '$timeout',
            function($scope, $state, $timeout) {

                var init = function() {}

            /*==========================
                Events
            ==========================*/

            /*==========================
                Members
            ==========================*/

            /*==========================
                Methods
            ==========================*/
            /*==========================
                init
            ==========================*/

            init();
        }
    ]}
}]);
