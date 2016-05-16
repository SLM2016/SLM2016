app.controller("CreateCoursePageController",['$scope', '$state', '$timeout', '$rootScope', '$q',
	function($scope, $state, $timeout, $rootScope, $q, UploadAttachmentService){
	var vm = this;
	var showCcList = []; 
	var ticketTypeList = [];   
	var ticketPriceList = [];   
	var showticketList = [];
	var updateticketsList = [];
	var showDateList = [];
	var typeSelected = null;
	var ticketTypeSelected = null;
	var typeTextBoxFlag = false ;
	var ticketTextBoxFlag = false ;
	
	this.selectedDropdownTypeItem = null;
	this.dropdownTypeItems = ['公開班', '企業內訓', '演講', '其他'];
	
	this.filterTypeList = function(userInput) {
        var filter = $q.defer();
        var normalisedInput = userInput.toLowerCase();
        var filteredArray = this.dropdownTypeItems.filter(function(country) {
        	return country.toLowerCase().indexOf(normalisedInput) === 0;
        });
        filter.resolve(filteredArray);
        return filter.promise;
    };
      
    this.itemTypeSelected = function(item) {
    	typeSelected = item;
    	if((item == '其他') && !typeTextBoxFlag){
    		var otherType = document.getElementById("otherTypeDiv");
    		var textbox = document.createElement("input");
    		textbox.type = "text";
    		textbox.name = "otherType";
    		otherType.appendChild(textbox);
    		typeTextBoxFlag = true;
    	}
    	else{
    		if(item != '其他' && typeTextBoxFlag){
    			var otherType = document.getElementById("otherTypeDiv");
    			otherType.innerHTML = "";
    			typeTextBoxFlag = false;
    		}
    	}
    };
    
    this.selectedDropdownTicketTypeItem = null;
    this.dropdownTicketTypeItems = ['原價', '早鳥', '泰迪之友', '四人團報', '其他'];
	
    this.filterTicketTypeList = function(userInput) {
        var filter = $q.defer();
        var normalisedInput = userInput.toLowerCase();
        var filteredArray = this.dropdownTicketTypeItems.filter(function(country) {
        	return country.toLowerCase().indexOf(normalisedInput) === 0;
        });
        filter.resolve(filteredArray);
        return filter.promise;
    };
      
    this.itemTicketTypeSelected = function(item) {
    	ticketTicketTypeSelected = item;
    	if((item == '其他') && !ticketTextBoxFlag){
    		var otherTicket = document.getElementById("otherTicket");
    		var textbox = document.createElement("input");
    		textbox.type = "text";
    		textbox.name = "otherTicket";
    		otherTicket.appendChild(textbox);
    		ticketTextBoxFlag = true;
    	}
    	else{
    		if(item != '其他' && ticketTextBoxFlag){
    			var otherTicket = document.getElementById("otherTicket");
    			otherTicket.innerHTML = "";
    			ticketTextBoxFlag = false;
    		}
    	}
    };
	
	function getTeddyCourseData() {
		$.get("/SLM2016/CourseManagerServlet",	function(responseText) {
			$scope.courseList = responseText;			
		});
	} 
	
	var changeloadType = function(type) {
         $scope.loadType = type;
    }
	
	function addCourse() {
		if(checkInput){
			var data = new Object();
			data.courseName_ = $scope.data.courseName;
			data.batch_ = $scope.data.batch;
			data.dates_ = $scope.showDateList;
			data.duration_ = $scope.data.duration;
			data.ticketTypes_ = $scope.ticketTypeList;
			data.prices_ = $scope.ticketPriceList;
			data.location_ = $scope.data.location;
			data.lecturer_ = $scope.data.lecturer;
			data.hyperlink_ = $scope.data.hyperlink;
			data.ccAddresses_ = $scope.showCcList;
			if(typeSelected=='其他'){
				var otherType = document.getElementsByName('otherType')[0].value;
				data.type_ = otherType;
			}
			else{
				data.type_ = typeSelected;
			}
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
		if(ticketTicketTypeSelected=='其他'){
			var otherTicket = document.getElementsByName('otherTicket')[0].value;
			ticketTicketTypeSelected = otherTicket;
		}
		if(((ticketTicketTypeSelected) == null) ||(($scope.data.price) == null)||(($scope.data.price.length) == 0)|((ticketTicketTypeSelected.length) == 0)){
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
					$scope.ticketTypeList.push(ticketTicketTypeSelected);
					$scope.ticketPriceList.push($scope.data.price);
					$scope.showticketList.push(ticketTicketTypeSelected + "  $" + $scope.data.price);
					ticketTicketTypeSelected = null;
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
				$scope.data.dateAddresses.setMinutes($scope.data.dateAddresses.getMinutes() - $scope.data.dateAddresses.getTimezoneOffset());
				$scope.showDateList.push($scope.data.dateAddresses.toISOString().substring(0, 10));
				$scope.data.dateAddresses = null;
			}
			else{
				window.alert("請輸入日期");
			}
		}
	}
	
	function clickAddCourseButton() {
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
		$scope.showticketList.splice(index,'1');
		$scope.ticketTypeList.splice(index,'1');
		$scope.ticketPriceList.splice(index,'1');
		
    }
	
	var deleteDate = function(index) {
		$scope.showDateList.splice(index,'1');
    }
	
	var deleteCc = function(index) {
		$scope.showCcList.splice(index,'1');
    }
	
	var fileChanged = function(files) {
		$scope.fileName = files;
    };
    
    var openDatePicker = function() {
        $scope.isDatePickerOpen = true;
    }
	
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
	$scope.showticketList = [];
	$scope.showCcList = [];
	$scope.showDateList = [];
	$scope.updateticketsList = [];
	$scope.isDatePickerOpen = false;
	$scope.format = 'yyyy 年 MM 月 dd 日';
    $scope.dateOptions = {
        locale: 'ru'
    };
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
	$scope.openDatePicker = openDatePicker;
	init();
}
]);