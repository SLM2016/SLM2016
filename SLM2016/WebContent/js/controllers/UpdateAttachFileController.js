app.controller("UpdateAttachFileController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope, UploadAttachmentService){
	    
	function getTeddyCourseData() {
		console.log($scope.courseList.length);
		$.get("/SLM2016/CourseManagerServlet",	function(responseText) {
			$scope.courseList = responseText;
		});
	} 
	
	var changeloadType = function(type) {
         $scope.loadType = type;
    }
	
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

	
	var clickUploadDataButton=function() {
		if (confirm("是否確認上傳!?") == true){
			uploadAttachment();
		}
		else {
			
		}
	}
	
	var deleteRow = function(index) {
		$.ajax({
			url : "/SLM2016/CourseManagerServlet",
			type : "POST",
			data : JSON.stringify(index),
			async : false,
			beforeSend: function (request)
            {
                request.setRequestHeader("Delete", false);
            }

		})
		
        getTeddyCourseData();
		setTimeout(function() {
			$scope.$apply(function() {
			    $scope.time = new Date();
			});
		}, 300);
    }
	
	var init = function() {
		getTeddyCourseData();
    }
	
	/*==========================
    Events
	==========================*/

	/*==========================
    Members
	==========================*/
	$scope.loadType = "Upload";
	$scope.courseList = [];
	/*==========================
     Methods
	==========================*/

	/*==========================
     init
	==========================*/
	$scope.selectedValue;
	$scope.uploadAttachment = uploadAttachment;
	$scope.clickUploadDataButton = clickUploadDataButton;
	$scope.changeloadType = changeloadType;
	$scope.deleteRow = deleteRow;
	
	init();
}
]);