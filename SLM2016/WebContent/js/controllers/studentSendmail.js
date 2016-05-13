app.controller('StudentSendmailController', ['$scope', '$state', '$stateParams', '$timeout', '$rootScope', 'StudentInfoService',  
    function ($scope, $state, $stateParams, $timeout, $rootScope, StudentInfoService) {  

		var setValue = function(){
			var studentName = document.getElementById("studentName");  		
    		studentName.value = parse[index].name;
		}
		
		var ClickNextButton = function(){	
			var studentName = document.getElementById("studentName");		
			if(++index <= parse.length-1){	
				previousButton.disabled = "";
	    		studentName.value = parse[index].name;
			}	
			if(index == parse.length-1){
				nextButton.disabled = "disabled";
			}
		}
		
		var ClickPreviousButton = function(){	
			var studentName = document.getElementById("studentName");		
			if(--index >= 0){
				nextButton.disabled = "";
	    		studentName.value = parse[index].name;
			}	
			if(index == 0){
				previousButton.disabled = "disabled";
			} 		
		}

    	var init = function() { 		
    		parse = JSON.parse($stateParams.showData);
    		setValue();
    		if(parse.length > 0){
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
    	var nextButton = document.getElementById("next");
    	var previousButton = document.getElementById("previous");
    	
    	$scope.ClickNextButton = ClickNextButton;
    	$scope.ClickPreviousButton = ClickPreviousButton;
    	
        init();
		
}]);