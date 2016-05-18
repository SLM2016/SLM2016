app.controller('StudentManageController', ['$scope', '$state', '$timeout', '$rootScope', 'StudentInfoService', 'CourseService',
    function ($scope, $state, $timeout, $rootScope, StudentInfoService, CourseService) {  

        var getCourseList = function() {
            $scope.isCourseLoading = true;
            $scope.isStudentLoading = true;
            CourseService.getCourseSimpleList().then(function(result) {
                $scope.isCourseLoading = false;
                for (var i = 0; i < result.length; i++) {
                    $scope.courseList.push(result[i]);
                }
                $scope.currentCourse = $scope.courseList[0];
                getStudentList()
            }, function(error) {
                $scope.isCourseLoading = false;
                $scope.isStudentLoadError = true;
            })
        }

        var changeStudentList = function(course) {
            $scope.currentCourse = course;
            $scope.studentList = [];
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

        var changeStudentStatus = function(student,num) {
            student.isSelected = !student.isSelected;
            console.log(student);
            if(num == 0)
                student.payment_status = "免繳費";
            else if (num == 1)
                student.payment_status = "未繳費";
            else if (num == 2)
                student.payment_status = "課後再繳費";
            else if (num == 3)
                student.payment_status = "已繳費";

            StudentInfoService.updateStudentReceiptStatus(student.receipt_EIN,student.payment_status,student.receipt_status,student.id).then(function(result) 
            {
                for (var i = 0; i < $scope.studentList.length; i++) {
                    if($scope.studentList[i].id == student.id) {
                        $scope.studentList[i].receipt_EIN = student.receipt_EIN;
                        $scope.studentList[i].payment_status = student.payment_status;
                        $scope.studentList[i].receipt_status = student.receipt_status;
                        break;
                    }
                }
                console.log(result);
            }, function(error) {
                console.log(error);
            })
        }

        var openInvoiceModal = function(student) {
            student.isSelected = !student.isSelected;

            var index = 0;
            for (var i = 0; i < $scope.studentList.length; i++) {
                if($scope.studentList[i].id == student.id) {
                    index = i;
                    break;
                }
            }
            $rootScope.$broadcast("OPEN_INVOICE_MODAL", {
                list: $scope.studentList,
                index: index,
                course: $scope.currentCourse
            });
        }
        
        var openCertificationModal = function(student) {
            student.isSelected = !student.isSelected;

            var index = 0;
            for (var i = 0; i < $scope.studentList.length; i++) {
                if($scope.studentList[i].id == student.id) {
                    index = i;
                    break;
                }
            }
            $rootScope.$broadcast("OPEN_Certification_MODAL", {
                list: $scope.studentList,
                index: index,
                course: $scope.currentCourse
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
        $scope.openInvoiceModal = openInvoiceModal;
        $scope.openCertificationModal = openCertificationModal;
        $scope.changeStudentList = changeStudentList;
        /*==========================
             init
        ==========================*/

        init();
		
}]);