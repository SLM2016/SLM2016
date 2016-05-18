app.controller('StudentSendmailController', ['$scope', '$state', '$timeout', '$rootScope', 'StudentInfoService',  
    function ($scope, $state, $timeout, $rootScope, StudentInfoService) {  

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
				parse[index].courseName+'<br>'+
				parse[index].studentName
				+"";
				CK=CKEDITOR.instances['editor1'];
				if (CK) {
					   CKEDITOR.remove(CKEDITOR.instances['editor1']); //Does the same as line below
					   CKEDITOR.add(CK);
				 }
				CKEDITOR.instances.editor1.setData(con);
			}


		
		var Send =function(){	
			var mailData = new Object();
			mailData.addresses_ = parse[index].address;
			mailData.text_ = getcontent();
			mailData.attachment_ = "1231231";
			
			$.ajax({
			    url: 'SendGmailServlet',
			    type: 'post',
			    data: JSON.stringify(mailData),
			    headers: {
			    	isSendCertification: 1
			    },
			    dataType: 'json',
			    success: function (data) {
			        console.log(data);
			    }
			});
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
				setValue();
			}	
			if(index == parse.length-1){
				nextButton.disabled = "disabled";
			}
		
			setemailcontent();
		}
		
		var ClickPreviousButton = function(){	
			
			if(--index >= 0){
				nextButton.disabled = "";
				setValue();
			}	
			if(index == 0){
				previousButton.disabled = "disabled";
			} 		
			
			setemailcontent();
		}

    	var init = function() { 		
    		parse = JSON.parse(StudentInfoService.getStudentSendMailData());
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
    	

    	$scope.Send = Send;
    	$scope.ClickNextButton = ClickNextButton;
    	$scope.ClickPreviousButton = ClickPreviousButton;
    	
        init();
		
}]);