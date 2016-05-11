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
            var preview_submit_disable = document.getElementById("preview_submit");
            
            for(var j = 0; j < $scope.studentList.length; j++){
            	if($scope.studentList[j].isSelected){
            		i++;
            	}
            }
            
            if(i > 0){           	
        		preview_submit_disable.disabled = "";
            }
            else{
            	preview_submit_disable.disabled = "disabled";
            }
        }
        
        var sentPreviewData = function(){
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
            temp.previewBox.value = JSON.stringify(previewData);
            
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
        $scope.sentPreviewData = sentPreviewData;
               
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