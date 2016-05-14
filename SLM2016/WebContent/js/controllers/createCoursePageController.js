app.controller("CreateCoursePageController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope, UploadAttachmentService){
	var ShowCcList = []; 
	var ticketTypeList = [];   
	var ticketPriceList = [];   
	var ShowticketList = [];
	var updateticketsList = [];
	var ShowDateList=[];
	var data = new Object();
	
	function getTeddyCourseData() {
		$.get("/SLM2016/CourseManagerServlet",	function(responseText) {
			console.log(responseText)
			$scope.courseList = responseText;			
		});
	} 
	
	var changeloadType = function(type) {
         $scope.loadType = type;
    }
	
	function addCourse() {
		if(checkInput){
		data.courseName_ = $scope.data.courseName;
		data.batch_ = $scope.data.batch;
		data.dates_ = $scope.ShowDateList;
		data.duration_ = $scope.data.duration;
		data.ticketTypes_ = $scope.ticketTypeList;
		data.prices_ = $scope.ticketPriceList;
		data.location_ = $scope.data.location;
		data.lecturer_ = $scope.data.lecturer;
		data.hyperlink_ = $scope.data.hyperlink;
		data.ccAddresses_ = $scope.ShowCcList;
		data.type_ = $scope.data.type;
		data.status_ = "準備中";
		$.post("/SLM2016/CourseManagerServlet",
				JSON.stringify(data)).done(function(data) {
				window.alert(data);
				getTeddyCourseData();
		});
		}
	}

	var clickAddTicketButton=function() {
		var regex=/^[a-zA-Z]+$/;
		if((($scope.data.ticketType) == null) ||(($scope.data.price) == null)||(($scope.data.price.length) == 0)|(($scope.data.ticketType.length) == 0)){
			window.alert("課程票價或票種請修正");
		}
		else{
			if ((($scope.data.price).match(regex))){
				window.alert("票價需要為數字");
			}
			else{
				if ((($scope.data.price)>2147483647)||(($scope.data.price)<0)){
				window.alert("課程金額過大");
				}
				else{
				$scope.ticketTypeList.push($scope.data.ticketType);
				$scope.ticketPriceList.push($scope.data.price);
				$scope.ShowticketList.push("         票種:  " +$scope.data.ticketType + "價格:  " + $scope.data.price);
				$scope.data.ticketType = null;
				$scope.data.price = null;
				}
			}
		}
	}
	
	var clickAddCcButton=function() {
	    var lastAtPos = ($scope.data.ccAddresses).lastIndexOf('@');
	    var lastDotPos = ($scope.data.ccAddresses).lastIndexOf('.');
		if((($scope.data.ccAddresses) == null)||(($scope.data.ccAddresses) == 0)){
			window.alert("請輸入副本收件者地址");
		}
		else{
			if(lastAtPos < lastDotPos && lastAtPos > 0 && ($scope.data.ccAddresses).indexOf('@@') == -1 && lastDotPos > 2 && (($scope.data.ccAddresses).length - lastDotPos) > 2){
				$scope.ShowCcList.push($scope.data.ccAddresses);
				$scope.data.ccAddresses = null;
			}
			else{
				window.alert("副本收件者地址請修正");
			}
		}
	}
	
	var clickAddDateButton=function() {
		if((($scope.data.dateAddresses) == null)){
			window.alert("請輸入日期");
		}
		else{
			if((($scope.data.dateAddresses.length) != 0)){
				$scope.ShowDateList.push($scope.data.dateAddresses);
				$scope.data.dateAddresses = null;
			}
			else{
				window.alert("請輸入日期");
			}
		}
	}
	
	var clickAddCourseButton=function() {
		if (confirm("是否確認開課!?") == true){
			addCourse();
		}
		else {
			
		}
	}
	
	var checkInput = function()	{
		if((($scope.data.courseName)== null)){
			window.alert("課程名稱欄位不可為空白");
			return false;
		}
		if((($scope.data.courseName.length)== 0)){
			window.alert("欄位不可為空白");
			return false;
		}
		return true;
	}
	
	var deleteRow = function(id) {
		$.ajax({
			url : "/SLM2016/CourseManagerServlet",
			type : "POST",
			data : id,
			dataType : "text",
			headers :
			{
				Delete : true
			},
			success : function(){
				getTeddyCourseData();
			}
        })
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
	
	var deleteDate = function(index) {
		$scope.ShowDateList.splice(index,'1');
    }
	
	var deleteCc = function(index) {
		$scope.ShowCcList.splice(index,'1');
    }
	
	var fileChanged = function(files) {
		$scope.fileName = files;
    };

	
	var init = function() {
		data = null;
		getTeddyCourseData();
    }
	
	/*
	 * ========================== Events ==========================
	 */

	/*
	 * ========================== Members ==========================
	 */
	$scope.loadType = "Upload";
	$scope.courseList = [];
	$scope.ticketTypeList = [];
	$scope.ticketPriceList = [];
	$scope.ShowticketList = [];
	$scope.ShowCcList = [];
	$scope.ShowDateList = [];
	$scope.updateticketsList = [];
	/*
	 * ========================== Methods ==========================
	 */

	/*
	 * ========================== init ==========================
	 */

	$scope.selectedValue;
	$scope.fileChanged = fileChanged;
	$scope.clickAddCourseButton = clickAddCourseButton;
	$scope.clickAddTicketButton = clickAddTicketButton;
	$scope.clickAddDateButton = clickAddDateButton;
	$scope.clickAddCcButton = clickAddCcButton;
	$scope.changeloadType = changeloadType;
	$scope.deleteRow = deleteRow;
	$scope.deleteTicket = deleteTicket;
	$scope.deleteDate = deleteDate;
	$scope.deleteCc = deleteCc;
	init();
}
]);