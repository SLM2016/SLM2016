app.controller("UpdateAttachFileController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope, UploadAttachmentService){
    
	function getTeddyCourseData() {
		$.get("/SLM2016/CourseManagerServlet",	function(responseText) {
			courseList = responseText;

		});
	} 
	
	var changeloadType = function(type) {
         clearInvoiceResult();
         $scope.loadType = type;
    }
	
	var clearInvoiceResult = function() {
 
    }
	
	var uploadAttachment = function() {
		var course = document.getElementById('Course').value;
		var batch = document.getElementById('Batch').value;
		var date = document.getElementById('Date').value;
		var duration = document.getElementById('Duration').value;
		var ticketType = document.getElementById('TicketType').value;
		var price = document.getElementById('Price').value;
		var location = document.getElementById('Location').value;
		var lecturer = document.getElementById('Lecturer').value;
		var status = document.getElementById('Status').value;

		var data = new Object();
		data.courseName_ = course;
		data.batch_ = batch;
		data.date_ = date;
		data.duration_ = duration;
		data.ticketType_ = ticketType;
		data.price_ = price;
		data.location_ = location;
		data.lecturer_ = lecturer;
		data.status_ = status;
		
		$.post("/SLM2016/CourseManagerServlet",
				JSON.stringify(data)).done(function(data) {
				window.alert(data);
				updateStudentInfo();
		});
	}

	var updateStudentInfo = function (){
		getTeddyCourseData();
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
	$scope.updateStudentInfo = updateStudentInfo;
	init();
}
]);