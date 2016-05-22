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
        			mailData[i].address = $scope.studentList[j].email;      			
        			i++;
        		} 
        	}        	
        	
        	StudentInfoService.getSendMailInfo(JSON.stringify(mailData)).then(function(courseData) {
                var parse = JSON.parse(JSON.stringify(courseData));
                for(var i = 0, j = 0; i < mailData.length; i++){
                	if( (j < parse.length) && (parse[j].id == mailData[i].courseId)){
                		var date = new Date(parse[j].date);
                		var duration = Number(parse[j].duration);
                		mailData[i].courseName = parse[j].name;
                    	mailData[i].couresDuration = numberToChinese(duration);
                    	mailData[i].courseDate = date.getFullYear()+ " 年 " + (date.getMonth()+1) + " 月 " + date.getDate() + " 日";
                    	j++;
                	}       
                }  
                StudentInfoService.putStudentSendMailData(mailData);
                $state.go('studentInfo.Sendmail');
            }, function(error) {
            	console.log('Get DB Data Has Error');
            })                     
        }
        
        var numberChar = ["零","一","二","三","四","五","六","七","八","九"];
        var unitChar = ["","十"];
        
        function numberToChinese(number){
            var result = '';
            
            if(number === 0){
                return numberChar[0];
            }

            while(number > 0){
                var section = number % 100;
                result = sectionToChinese(section);
                number = Math.floor(number / 100);
            }
            if((result.length >= 2) && (result.indexOf("一") == 0)){
            	result = result.replace("一","");
            }
             
            return result;
        }
        
        function sectionToChinese(section){
            var tempChar = '', result = '';
            var unitCharIndex = 0;
            while(section > 0){
                var numberCharIndex = section % 10;
                if(numberCharIndex !==0){
                	tempChar = numberChar[numberCharIndex];
                    tempChar += unitChar[unitCharIndex]
                    result = tempChar + result;
                }             
                             
                unitCharIndex++;
                section = Math.floor(section / 10);
            }
           
            return result;
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
        $scope.sendMailData = sendMailData;
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
        $scope.changeStudentList = changeStudentList;
        /*==========================
             init
        ==========================*/

        init();
		
}]);