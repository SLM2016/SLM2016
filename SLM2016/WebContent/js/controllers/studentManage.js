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
        	$scope.isCertificationLoading = true;
        	var mailData = [];
        	var i = 0;
        	for(var j = 0; j < $scope.studentList.length; j++){
        		
        		if($scope.studentList[j].isSelected){
        			mailData[i] = new Object();
        			mailData[i].studentId = $scope.studentList[j].id;
        			mailData[i].studentName = $scope.studentList[j].name;
        			mailData[i].courseId = $scope.studentList[j].fk_course_info_id;
        			mailData[i].address = $scope.studentList[j].email;
        			if($scope.studentList[j].certificationImg != null){
        				mailData[i].certificationImg = $scope.studentList[j].certificationImg;
        				mailData[i].certificationPdf = $scope.studentList[j].certificationPdf;
        			}
        			else{
        				mailData[i].certificationImg = "";
        				mailData[i].certificationPdf = "";
        			}
        			i++;
        		} 
        	}        	
        	
        	StudentInfoService.getSendMailInfo(JSON.stringify(mailData)).then(function(courseData) {
                var parse = JSON.parse(JSON.stringify(courseData));
                for(var i = 0, j = 0; i < mailData.length; i++){
                	if( (j < parse.length) && (parse[j].id == mailData[i].courseId)){
                		mailData[i].courseName = parse[j].name;
                    	mailData[i].couresDuration = parse[j].duration;
                    	mailData[i].date = parse[j].dates;
                    	j++;
                	}                	
                }
                         
                for(var i = 0; i < mailData.length; i++){
                	if(mailData[i].certificationImg == ""){
                		makeCertification(i, mailData);                    		
                		}
                }
                
                StudentInfoService.putStudentSendMailData(mailData);
                $state.go('studentInfo.Sendmail');
            }, function(error) {
            	console.log('Get DB Has Error');
            })
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
    	
    	var makeCertification = function(index , maildata){
    		//製作證書
    		var data = new Object();
    		var Today = new Date();
    		data.id_ = maildata[index].studentId;
    		data.owner_ =  maildata[index].studentName;
    		data.date_ =  Today.getFullYear()+ " 年 " + (Today.getMonth()+1) + " 月 " + Today.getDate() + " 日" ;
    		data.courceDate_ = maildata[index].couresDate;
    		data.courceName_ =  maildata[index].courseName;
    		data.courcourceDuration_ =  maildata[index].couresDuration;
    		    		
    		CertificationPostToDB(data, maildata[index].studentId, index);
    	}
    	    	
    	var CertificationPostToDB = function(data, studentId, index){
    		
    		$.post("/SLM2016/CertificationServlet",JSON.stringify(data))
			.done(function(imageString){
				var imageData = new Object();
				
				$.post("/SLM2016/CertificationServlet",JSON.stringify(data))
				.done(function(pdfString){
					//將Pdf寫入DB
					StudentInfoService.updateStudentCertificationPdf(pdfString, studentId, index);
				});
				//將Image寫入DB
				StudentInfoService.updateStudentCertificationImage(imageString, studentId, index);
			});
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
    	$scope.isCertificationLoading = false;
               
        /*==========================
             Methods
        ==========================*/

        $scope.toggleSelectStudent = toggleSelectStudent;
        $scope.toggleStatusDropdown = toggleStatusDropdown;
        $scope.toggleActionDropdown = toggleActionDropdown;
        $scope.sendMailData = sendMailData;
        $scope.changeStudentStatus = changeStudentStatus;
        $scope.openInvoiceModal = openInvoiceModal;
        $scope.changeStudentList = changeStudentList;
        /*==========================
             init
        ==========================*/

        init();
		
}]);