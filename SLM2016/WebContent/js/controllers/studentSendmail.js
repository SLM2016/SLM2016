app.controller('StudentSendmailController', ['$scope', '$state', '$stateParams', '$timeout', '$rootScope', 'StudentInfoService',  
    function ($scope, $state, $stateParams, $timeout, $rootScope, StudentInfoService) {  

			function getcontext(){
			  				  
			   var saveData = $.ajax({
			        url: "http://httpbin.org/post",
			        type: "POST",
			        data: { emailcontent:g, method: "example" },
			        dataType: "json"
			    });
			}
			function setcontent(d){
				var kk=getcontent();
				CKEDITOR.instances.editor1.setData(kk+d+'<br>');
			
			}
			function getcontent() {
			  	  return CKEDITOR.instances.editor1.getData();
			}
			function removeemailcontent() {
				
			}
			function setemailcontent(){
				var con='title<br>'+
				"Hi Erica "+
				courseName.value+'<br>'+
				studentName.value
				+"";
				CK=CKEDITOR.instances['editor1'];
				if (CK) {
					   CKEDITOR.remove(CKEDITOR.instances['editor1']); //Does the same as line below
					   CKEDITOR.add(CK);
				 }
				CKEDITOR.instances.editor1.setData(con);

			}


		
		var Sendcontext =function(){
			
	  	    
	  	}
	  
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
		
			setemailcontent();
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
			
			setemailcontent();
		}

    	var init = function() { 		
    		parse = JSON.parse($stateParams.showData);
    		setValue();
    		if(parse.length-1 > 0){
    			nextButton.disabled = "";
    		}
    	
    		 setemailcontent();
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
    	
    	 CKEDITOR.replace( 'editor1' );
    	
    	  
    	$scope.Sendcontext = Sendcontext;
    	$scope.ClickNextButton = ClickNextButton;
    	$scope.ClickPreviousButton = ClickPreviousButton;
    	
        init();
		
}]);