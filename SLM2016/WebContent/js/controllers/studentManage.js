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
            var sendMailDataButton = document.getElementById("sendMailData");
            
            for(var j = 0; j < $scope.studentList.length; j++){
            	if($scope.studentList[j].isSelected){
            		i++;
            	}
            }
            
            if(i > 0){           	
            	sendMailDataButton.disabled = "";
            }
            else{
            	sendMailDataButton.disabled = "disabled";
            }
        }
        
        var sendMailData = function(){
        	var mailData = [];
        	var i = 0;
        	
        	for(var j = 0; j < $scope.studentList.length; j++){
        		if($scope.studentList[j].isSelected){
        			mailData[i] = new Object();
        			mailData[i].studentId = $scope.studentList[j].id;
        			mailData[i].studentName = $scope.studentList[j].name;
        			mailData[i].courseId = $scope.studentList[j].fk_course_info_id;
        			mailData[i].date = $scope.studentList[j].timestamp;
        			mailData[i].address = $scope.studentList[j].email;
        			i++;
        		} 
        	}        	
        	
        	StudentInfoService.getSendMailInfo(JSON.stringify(mailData)).then(function(courseData) {
                var parse = JSON.parse(JSON.stringify(courseData));
                for(var i = 0, j = 0; i < mailData.length; i++){
                	if( (j < parse.length) && (parse[j].id == mailData[i].courseId)){
                		mailData[i].courseName = parse[j].name;
                    	mailData[i].couresDuration = parse[j].duration;
                    	j++;
                	}               	
                }  
                StudentInfoService.putStudentSendMailData(mailData);
                $state.go('studentInfo.Sendmail');
            }, function(error) {
            	console.log('Get DB Has Error');
            })                     
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
        $scope.sendMailData = sendMailData;
               
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