app.controller('StudentSendmailController', ['$scope', '$state', '$stateParams', '$timeout', '$rootScope', 'StudentInfoService',  
    function ($scope, $state, $stateParams, $timeout, $rootScope, StudentInfoService) {  

		var setValue = function(){ 		
			courseName.value = parse[index].courseName;
    		studentName.value = parse[index].studentName;
    		date.value = parse[index].date;
    		studentId.value = parse[index].studentId;
    		couresDuration.value = parse[index].couresDuration;
		}
		
		var ClickNextButton = function(){			
			if(++index <= parse.length-1){	
				previousButton.disabled = "";
				courseName.value = parse[index].courseName;
	    		studentName.value = parse[index].studentName;
	    		date.value = parse[index].date;
	    		studentId.value = parse[index].studentId;
	    		couresDuration.value = parse[index].couresDuration;
			}	
			if(index == parse.length-1){
				nextButton.disabled = "disabled";
			}
		}
		
		var ClickPreviousButton = function(){			
			if(--index >= 0){
				nextButton.disabled = "";
				courseName.value = parse[index].courseName;
	    		studentName.value = parse[index].studentName;
	    		date.value = parse[index].date;
	    		studentId.value = parse[index].studentId;
	    		couresDuration.value = parse[index].couresDuration;
			}	
			if(index == 0){
				previousButton.disabled = "disabled";
			} 		
		}

    	var init = function() { 		
    		parse = JSON.parse($stateParams.showData);
    		setValue();
    		if(parse.length-1 > 0){
    			nextButton.disabled = "";
    		}
        }

		/*==========================
            Events
        ==========================*/

        /*==========================
            Members
        ==========================*/

        /*==========================
             Methods
        ==========================*/

        /*==========================
             init
        ==========================*/
   	
    	var parse;
    	var index = 0;
    	var courseName = document.getElementById("courseName");	
    	var studentName = document.getElementById("studentName");	
    	var date = document.getElementById("date");	
    	var studentId = document.getElementById("studentId");	
    	var couresDuration = document.getElementById("couresDuration");	
    	
    	var nextButton = document.getElementById("next");
    	var previousButton = document.getElementById("previous");
    	
    	$scope.ClickNextButton = ClickNextButton;
    	$scope.ClickPreviousButton = ClickPreviousButton;
    	
        init();
		
}]);