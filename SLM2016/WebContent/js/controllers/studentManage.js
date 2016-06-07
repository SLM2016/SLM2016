app.controller('StudentManageController', ['$q','$scope', '$state', '$timeout', '$rootScope', 'StudentInfoService', 'CourseService','Upload',
    function ($q,$scope, $state, $timeout, $rootScope, StudentInfoService, CourseService , Upload) {  

        var getCourseList = function() {
            $scope.isCourseLoading = true;
            $scope.isStudentLoading = true;
            CourseService.getCourseSimpleList().then(function(result) {
                $scope.isCourseLoading = false;
                if(result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        $scope.courseList.push(result[i]);
                    }
                    $scope.currentCourse = $scope.courseList[0];
                    getStudentList()
                }
                else {
                    $scope.isCourseEmpty = true;
                }
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
        			mailData[i].certificationId = $scope.studentList[j].certification_id;    			
        			i++;
        		} 
        	}  
        	StudentInfoService.putStudentSendMailData(mailData);
            $state.go('studentInfo.Sendmail');      	
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

        var changeCertificationBackground=function(file) {
        	clearFile();
        	var previewBackground = document.getElementById("previewBackground");
        	var uploadpreviewBackground = document.getElementById("uploadpreviewBackground");
        	$scope.imgFile = file;
        	if($scope.imgFile!=null){
        		previewBackground.style.display="none";
        		uploadpreviewBackground.style.display="";
        	}
        	else
        		previewBackground.style.display="";
		} 
        
        var clearFile = function() {
	    	$scope.picFile = null;
	    	$scope.imgFile = null;
	    	$scope.imgFile;
		}
        
        var getBackgound=function() {
        	clearFile();
        	var uploadpreviewBackground = document.getElementById("uploadpreviewBackground");
        	uploadpreviewBackground.style.display="none";
        	var previewBackground = document.getElementById("previewBackground");
			previewBackground.style.display="none";
        	var data=new Object();
			data.id_="";
			data.owner_="";
			data.date_="";
			data.courceDate_="";
			data.courceName_="";
			data.courceDuration_="";
        	data.courceId_=$scope.currentCourse.courseId_;
			$.post("/SLM2016/CertificationServlet",JSON.stringify(data))
			.done(function(data)
			{
				document.getElementById("blah").setAttribute('src','data:image/png;base64,'+data);
				var previewBackground = document.getElementById("previewBackground");
				previewBackground.style.display="";
			});	
		} 
        
        
        
        var uploadBackground=function() {
        	if(!$scope.imgFile) {
        		window.alert("請選取檔案");
				return;
			}      	 
    		    var defer = $q.defer();
    		    Upload.upload({
    		        url: '/SLM2016/UpdateCertificationBackgroundServlet',
    		        withCredential: true,
    		        data: {
                        courseId: $scope.currentCourse.courseId_
    		        },
    		        file: $scope.imgFile
    		    }).success(function(data) {
    		    	if(data.status=="true"){
    		    		window.alert("上傳成功");
    		    		getBackgound();
    		    	}
    		    	else{
    		    		window.alert("上傳失敗");
    		    		console.log(data.status);
    		    	}
    			}).error(function(error) {
    				window.alert("上傳失敗");
    				console.log(error);
    			});   		    	
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
        $scope.isCourseEmpty = false;
        $scope.isStudentLoadError = false;
        $scope.isStudentLoading = false;
        $scope.studentList = [];
        $scope.sendMailData = sendMailData;
        $scope.courseList = [];
        $scope.currentCourse;
               
        /*==========================
             Methods
        ==========================*/
        $scope.uploadBackground = uploadBackground;
        $scope.getBackgound = getBackgound;
        $scope.changeCertificationBackground = changeCertificationBackground;
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