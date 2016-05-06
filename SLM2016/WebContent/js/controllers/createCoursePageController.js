app.controller("CreateCoursePageController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope, UploadAttachmentService){
	var ShowCcList = []; 
	var ticketTypeList = [];   
	var ticketPriceList = [];   
	var ShowticketList = [];
	
	
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
		data.ticketType_ = $scope.ticketTypeList;
		data.price_ = $scope.ticketPriceList;
		data.location_ = $scope.data.location;
		data.lecturer_ = $scope.data.lecturer;
		data.cc_ = $scope.data.cc;
		data.status_ = "未開課";
		
		$.post("/SLM2016/CourseManagerServlet",
				JSON.stringify(data)).done(function(data) {
				window.alert(data);
				getTeddyCourseData();
		});
	}

	var clickAddTicketButton=function() {
		$scope.ticketTypeList.push($scope.data.ticketType);
		$scope.ticketPriceList.push($scope.data.price);
		$scope.ShowticketList.push("         票種:  " +$scope.data.ticketType + "價格:  " + $scope.data.price);
		$scope.data.ticketType = null;
		$scope.data.price = null;
	}
	
	var clickAddCcButton=function() {
		$scope.ShowCcList.push($scope.data.cc);
		$scope.data.cc = null;
	}
	
	var clickAddCourseButton=function() {
		if (confirm("是否確認開課!?") == true){
			addCourse();
		}
		else {
			
		}
	}
	
	var checkInput = function()	{
		if((($scope.data.price)>2147483647)||(($scope.data.price)<0)){
			window.alert("課程金額請修正");
			return false;
		}
		if((($scope.data.courseName)== null)||(($scope.data.batch)== null)||(($scope.data.duration)== null)||(($scope.data.ticketPriceList)== null)||(($scope.data.location)== null)||(($scope.data.lecturer)== null)||(($scope.data.status)== null)){
			window.alert("欄位不可為空白");
			return false;
		}
		if((($scope.data.courseName.length)== 0)||(($scope.data.batch.length)== 0)||(($scope.data.duration.length)== 0)||(($scope.data.ticketPriceList.length)== 0)||(($scope.data.location.length)== 0)||(($scope.data.lecturer.length)== 0)||(($scope.data.status.length)== 0)){
			window.alert("欄位不可為空白");
			return false;
		}
		return true;
	}
	
	var deleteRow = function(index) {
		$.ajax({
			url : "/SLM2016/CourseManagerServlet",
			type : "POST",
			data : JSON.stringify(index),
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

	var deleteTicket = function(index) {
		$scope.ShowticketList.splice(index,'1');
		$scope.ticketTypeList.splice(index,'1');
		$scope.ticketPriceList.splice(index,'1');
    }
	
	var deleteCc = function(index) {
		$scope.ShowCcList.splice(index,'1');
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
	$scope.ticketTypeList = [];
	$scope.ticketPriceList = [];
	$scope.ShowticketList = [];
	$scope.ShowCcList = [];
	/*==========================
     Methods
	==========================*/

	/*==========================
     init
	==========================*/

	$scope.selectedValue;
	$scope.clickAddCourseButton = clickAddCourseButton;
	$scope.clickAddTicketButton = clickAddTicketButton;
	$scope.clickAddCcButton = clickAddCcButton;
	$scope.changeloadType = changeloadType;
	$scope.deleteRow = deleteRow;
	$scope.deleteTicket = deleteTicket;
	$scope.deleteCc = deleteCc;
	init();
}
]);