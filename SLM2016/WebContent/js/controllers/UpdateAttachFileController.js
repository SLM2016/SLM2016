app.controller("UpdateAttachFileController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope, UploadAttachmentService){
	    
	function getTeddyCourseData() {
		$.get("/SLM2016/CourseManagerServlet",	function(responseText) {
			$scope.courseList = responseText;
		});
	} 
	
	var changeloadType = function(type) {
         $scope.loadType = type;
    }
	
	function addCourse() {
		var data = new Object();
		data.courseName_ = $scope.data.courseName;
		data.batch_ = $scope.data.batch;
		data.date_ = $scope.data.date;
		data.duration_ = $scope.data.duration;
		data.ticketType_ = $scope.data.ticketType;
		data.price_ = $scope.data.price;
		data.location_ = $scope.data.location;
		data.lecturer_ = $scope.data.lecturer;
		data.status_ = "未開課";
		
		$.post("/SLM2016/CourseManagerServlet",
				JSON.stringify(data)).done(function(data) {
				window.alert(data);
				getTeddyCourseData();
		});
	}

	var clickAddCourseButton=function() {
		if (confirm("是否確認開課!?") == true){
			addCourse();
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
                request.setRequestHeader("Delete", true);
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
	$scope.clickAddCourseButton = clickAddCourseButton;
	$scope.changeloadType = changeloadType;
	$scope.deleteRow = deleteRow;
	
	init();
}
]);