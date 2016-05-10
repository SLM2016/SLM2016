app.controller('StudentManageController', ['$scope', '$state', '$timeout', '$rootScope', 'StudentInfoService', 
    function ($scope, $state, $timeout, $rootScope, StudentInfoService) {  

        var getClassList = function() {
            // TODO : 取得課程列表
            $scope.studentList = [];
        }

        var getStudentList = function() {
            $scope.isStudentLoading = true;
            StudentInfoService.getStudentList($scope.currentClassId).then(function(result) {
                $scope.isStudentLoading = false;
                for (var i = 0; i < result.length; i++) {
                    result[i].isSelected = false;
                    $scope.studentList.push(result[i]);
                }
                $scope.studentList = result;
                console.log($scope.studentList)
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
            getStudentList();
        }

		/*==========================
            Events
        ==========================*/

        /*==========================
            Members
        ==========================*/

        $scope.currentClassId = "123";
        $scope.isStudentLoadError = false;
        $scope.isStudentLoading = false;
        $scope.studentList = [];

        /*==========================
             Methods
        ==========================*/

        $scope.toggleSelectStudent = toggleSelectStudent;
        $scope.toggleStatusDropdown = toggleStatusDropdown;
        $scope.toggleActionDropdown = toggleActionDropdown;
        $scope.changeStudentStatus = changeStudentStatus;
        $scope.openInvoiceModel = openInvoiceModel;
        /*==========================
             init
        ==========================*/

        init();
		
}]);