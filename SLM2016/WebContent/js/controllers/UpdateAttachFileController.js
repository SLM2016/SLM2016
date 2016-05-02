app.controller("UpdateAttachFileController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope, UploadAttachmentService){
    
	function getTeddyCourseData() {
		$.get("/SLM2016/CourseManagerServlet",	function(responseText) {
			courseList = responseText;
			updateStudentInfo();
//			console.log(courseList);
//			console.log(courseList[0].courseName_);
		});
	} 
	
	var changeloadType = function(type) {
         //clearInvoiceResult();
         $scope.loadType = type;
    }
	
//	function clearInvoiceResult() {
//
//    }
	
	var uploadAttachment = function() {
		var data = new Object();
		data.courseName_ = $scope.data.courseName;
		data.batch_ = $scope.data.batch;
		data.date_ = $scope.data.date;
		data.duration_ = $scope.data.duration;
		data.ticketType_ = $scope.data.ticketType;
		data.price_ = $scope.data.price;
		data.location_ = $scope.data.location;
		data.lecturer_ = $scope.data.lecturer;
		data.status_ = $scope.data.status;
		
		$.post("/SLM2016/CourseManagerServlet",
				JSON.stringify(data)).done(function(data) {
				window.alert(data);
				getTeddyCourseData();
		});
	}

	function updateStudentInfo(){
		var temp_name = document.createElement('temp');
		temp.manergeStudentPreviewBox.value = courseList;
	}
	
	var clickUploadDataButton=function() {
		if (confirm("是否確認上傳!?") == true){
			uploadAttachment();
		}
		else {
			
		}
	}
	
	var init = function() {
		courseList = new Array();
    }
	
	/*==========================
    Events
	==========================*/

	/*==========================
    Members
	==========================*/
	$scope.loadType = "Upload";
	/*==========================
     Methods
	==========================*/

	/*==========================
     init
	==========================*/
	
	$scope.uploadAttachment = uploadAttachment;
	$scope.clickUploadDataButton = clickUploadDataButton;
	$scope.changeloadType = changeloadType;
	init();
}
]);