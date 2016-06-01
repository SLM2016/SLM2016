app.controller("CourseManageController", ['$scope', '$state', '$timeout', '$rootScope', '$q', 'CourseService',
    function($scope, $state, $timeout, $rootScope, $q, CourseService) {

        var getTeddyCourseData = function() {
            $scope.isCourseLoading = true;
            console.log("getTeddyCourseData");
            CourseService.getCourseList().then(function(result) {
                $scope.isCourseLoading = false;
                $scope.courseList = result;
            }, function(error) {
                $scope.isCourseLoading = false;
            })
        }

        var deleteRow = function(id) {
            $.ajax({
                url: "/SLM2016/CourseManagerServlet",
                type: "POST",
                data: id,
                dataType: "text",
                headers: {
                    Delete: true
                },
                success: function() {
                    getTeddyCourseData();
                }
            })
            setTimeout(function() {
                $scope.$apply(function() {
                    $scope.time = new Date();
                });
            }, 500);
        }

        var init = function() {
            getTeddyCourseData();
        }

        /*==========================
            Members
        ==========================*/

        $scope.courseList = [];
        $scope.isCourseLoading = false;

        /*==========================
            Methods
        ==========================*/

        $scope.deleteRow = deleteRow;

        /*==========================
            Init
        ==========================*/

        init();
    }
]);