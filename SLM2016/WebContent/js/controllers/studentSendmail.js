app.controller('StudentSendmailController', ['$scope', '$state', '$stateParams', '$timeout', '$rootScope', 'StudentInfoService',  
    function ($scope, $state, $stateParams, $timeout, $rootScope, StudentInfoService) {  
	
		var Sendcontext =function(){
			console.debug(tinyMCE.activeEditor.getContent());
	  	    var i=tinyMCE.get("con").getContent();
	  	    var g=tinyMCE.activeEditor.getContent({format : 'raw'});
	  	      var saveData =
  	    	$.ajax({url: "http://httpbin.org/post",
		  	        type: "POST",
		  	        data: { emailcontent:g, method: "example"},
		  	        dataType: "json"});
	  	    
	  	    
	  	}
	  
		var setValue = function(){ 		
			courseName.value = parse[index].courseName;
    		studentName.value = parse[index].studentName;
    		date.value = parse[index].date;
    		studentId.value = parse[index].studentId;
    		couresDuration.value = parse[index].couresDuration;
		}
		
		var ClickNextButton = function(){	
			console.debug(tinyMCE.activeEditor.getContent());
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
    		tinymce.init({  selector:'textarea',
			    			theme:"modern",
			    			plugins: [" autolink link"],
			    			menubar: false,toolbar: "link" });
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
    	
    	var sendButton = document.getElementById("send");
    	
    	
    	$scope.Sendcontext = Sendcontext;
    	$scope.ClickNextButton = ClickNextButton;
    	$scope.ClickPreviousButton = ClickPreviousButton;
    	
        init();
		
}]);