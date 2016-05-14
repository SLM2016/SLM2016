app.controller('StudentManageController', ['$scope', '$state', '$timeout', '$rootScope', 'StudentInfoService', 'CourseService',
    function ($scope, $state, $timeout, $rootScope, StudentInfoService, CourseService) {  

        var getCourseList = function() {
            $scope.isStudentLoading = true;
            CourseService.getCourseList().then(function(result) {
                for (var i = 0; i < result.length; i++) {
                    $scope.courseList.push(result[i]);
                }
                $scope.currentCourse = $scope.courseList[0];
                getStudentList()
            }, function(error) {
                $scope.isStudentLoading = false;
                $scope.isStudentLoadError = true;
            })
        }

        var changeStudentList = function(course) {
            $scope.currentCourse = course;
            getStudentList();
        }

        var getStudentList = function() {
            $scope.isStudentLoading = true;
            StudentInfoService.getStudentListByCourseId($scope.currentCourse.courseId_).then(function(result) {
                $scope.isStudentLoading = false;
                for (var i = 0; i < result.length; i++) {
                    result[i].isSelected = false;
                    $scope.studentList.push(result[i]);
                }
                $scope.studentList = result;
            }, function(error) {
                $scope.isStudentLoading = false;
                $scope.isStudentLoadError = true;
            })
        }

        var toggleSelectStudent = function(student) {
            student.isSelected = !student.isSelected;
        }

        var toggleStatusDropdown = function(student) {
            student.isSelected = !student.isSelected;
        }

        var toggleActionDropdown = function(student) {
            student.isSelected = !student.isSelected;
        }

        var changeStudentStatus = function(student) {
            student.isSelected = !student.isSelected;
        }

        var openInvoiceModel = function(student) {
            student.isSelected = !student.isSelected;

            var index = 0;
            for (var i = 0; i < $scope.studentList.length; i++) {
                if($scope.studentList[i].id == student.id) {
                    index = i;
                    break;
                }
            }
            $rootScope.$broadcast("OPEN_INVOICE_MODEL", {
                list: $scope.studentList,
                index: index
            });
        }

    	var init = function() {
            getCourseList();
        }

		/*==========================
            Events
        ==========================*/

        /*==========================
            Members
        ==========================*/

        $scope.isStudentLoadError = false;
        $scope.isStudentLoading = false;
        $scope.studentList = [];
        $scope.courseList = [];
        $scope.currentCourse;

        /*==========================
             Methods
        ==========================*/

        $scope.toggleSelectStudent = toggleSelectStudent;
        $scope.toggleStatusDropdown = toggleStatusDropdown;
        $scope.toggleActionDropdown = toggleActionDropdown;
        $scope.changeStudentStatus = changeStudentStatus;
        $scope.openInvoiceModel = openInvoiceModel;
        $scope.changeStudentList = changeStudentList;
        /*==========================
             init
        ==========================*/

        init();
		
}]);