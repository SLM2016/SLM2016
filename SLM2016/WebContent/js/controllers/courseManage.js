app.controller("CourseManageController", ['$scope', '$state', '$timeout', '$rootScope', '$q', 'CourseService',
    function($scope, $state, $timeout, $rootScope, $q, CourseService) {

        var getTeddyCourseData = function() {
            $scope.isCourseLoading = true;
            $scope.courseGroupList = [];
            console.log("getTeddyCourseData");
            CourseService.getCourseList().then(function(result) {
                for (var i = 0; i < result.length; i++) {
                    if (!isInCourseGroup(result[i])) {
                        var courseGroup = {
                            name: result[i].courseName_,
                            courseList: [],
                            openLevel: true
                        }
                        for (var j = 0; j < result.length; j++) {
                            if (courseGroup.name == result[j].courseName_) {
                                courseGroup.courseList.push(result[j]);
                            }
                        }
                        $scope.courseGroupList.push(courseGroup);
                    }
                }
                $scope.isCourseLoading = false;
                console.log($scope.courseGroupList)
            }, function(error) {
                $scope.isCourseLoading = false;
            });
        }

        var isInCourseGroup = function(course) {
            var isInGroup = false
            for (var i = 0; i < $scope.courseGroupList.length; i++) {
                if ($scope.courseGroupList[i].name == course.courseName_) {
                    isInGroup = true;
                    break;
                }
            }
            return isInGroup;
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

        var openGroupLevel = function(group) {
            group.openLevel = !group.openLevel;
        }
        var goStudentManage = function(courseId) {
            $state.go(STATES.COURSEINFO_STUDENT, {
                courseId: courseId
            })
        }

        var init = function() {
            getTeddyCourseData();
        }

        /*==========================
            Members
        ==========================*/

        $scope.courseGroupList = [];
        $scope.isCourseLoading = false;

        /*==========================
            Methods
        ==========================*/

        $scope.deleteRow = deleteRow;
        $scope.goStudentManage = goStudentManage;
        $scope.openGroupLevel = openGroupLevel;

        /*==========================
            Init
        ==========================*/

        init();
    }
]);
