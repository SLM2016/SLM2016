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

        var toggleSelectStudent = function($event, student) {
            student.isSelected = !student.isSelected
        }

        var reverseSelect = function(student) {
            student.isSelected = !student.isSelected
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
        $scope.reverseSelect = reverseSelect;
        /*==========================
             init
        ==========================*/

        init();
		
}]);