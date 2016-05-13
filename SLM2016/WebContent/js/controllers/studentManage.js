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
            
            var i = 0;
            var sendButton = document.getElementById("send");
            
            for(var j = 0; j < $scope.studentList.length; j++){
            	if($scope.studentList[j].isSelected){
            		i++;
            	}
            }
            
            if(i > 0){           	
            	sendButton.disabled = "";
            }
            else{
            	sendButton.disabled = "disabled";
            }
        }
        
        var sendData = function(){
        	var previewData = [];
        	var i = 0;
        	for(var j = 0; j < $scope.studentList.length; j++){
        		if($scope.studentList[j].isSelected){
        			previewData[i] = new Object();
        			previewData[i].name = $scope.studentList[j].name;
        			previewData[i].company =  $scope.studentList[j].company;
        			previewData[i].phone =  $scope.studentList[j].phone;
        			previewData[i].email =  $scope.studentList[j].email;
        			i++;
        		} 
        	}        	
            
        	// Test Data
        	previewData[0] = new Object();
			previewData[0].name = '陳';
			previewData[1] = new Object();
			previewData[1].name = '泰';
			previewData[2] = new Object();
			previewData[2].name = '迪';
            $state.go('studentInfo.Sendmail', {'showData' : JSON.stringify(previewData)});
            
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
        $scope.sendData = sendData;
               
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