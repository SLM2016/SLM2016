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
				var con="Hi "+
				parse[index].studentName+",<br><br>"+
				"很開心這次和大家一起進行了兩天的課程，希望透過上課的講解與實作練習能對"+parse[index].courseName+"有更深的瞭解與應用的機會。<br><br>"+
				"附件為本次課程證書，請參考。<br><br>"+
				"課程照片將放在："+'<a href="https://www.facebook.com/groups/ezScrum/">https://www.facebook.com/groups/ezScrum/</a>'+"<br><br>"+
				
				"我們也會持續舉辦C.C. Agile每月聚會或不定期舉辦泰迪軟體學員同樂會，歡迎和我們保持聯絡，課後任何有疑問都可以來聊聊。<br><br>"+
				"更多消息可參考："+"<br>"+
				"搞笑談軟工FB社團"+ '<a href="https://www.facebook.com/groups/teddy.tw/">https://www.facebook.com/groups/teddy.tw/</a>'+"<br>"+
				"泰迪軟體敏捷開發課程社團"+ '<a href="https://www.facebook.com/groups/ezScrum/">https://www.facebook.com/groups/ezScrum/</a>'+"<br>";
				CK=CKEDITOR.instances['editor1'];
				if (CK) {
					   CKEDITOR.remove(CKEDITOR.instances['editor1']); //Does the same as line below
					   CKEDITOR.add(CK);
				 }
				CKEDITOR.instances.editor1.setData(con);
			}


		
		var Send =function(){	
			var mailData = new Object();
			mailData.id_ = parse[index].studentId;
			mailData.courseName_ = parse[index].courseName;
			mailData.addresses_ = parse[index].address;
			mailData.text_ = getcontent();
			
			$.ajax({
			    url: 'SendGmailServlet',
			    type: 'post',
			    data: JSON.stringify(mailData),
			    headers: {
			    	isSendCertification: 1
			    },
			    dataType: 'json',
			    success: function (data) {		        
			        alert(data);
			    }
			});
		}
	  
		var setValue = function(){ 		
			courseName.value = parse[index].courseName;
    		studentName.value = parse[index].studentName;
    		date.value = parse[index].courseDate;
    		studentId.value = parse[index].studentId;
    		couresDuration.value = parse[index].couresDuration; 
    		
    		StudentInfoService.getCertificationInfo(JSON.stringify(parse[index].studentId)).then(function(certificationData) {
    			var parse = JSON.parse(JSON.stringify(certificationData));   			
    			
    			if(parse[0].certification_img == ""){
    				makeCertificationIMG();
    			}  
    			else{
    				console.log("DB 有資料，直接顯示");
    				document.getElementById("certificationImg").setAttribute('src','data:image/png;base64,'+parse[0].certification_img);
    			}
            }, function(error) {
            	console.log('Get DB Data Has Error');
            });		
		}
		
		var makeCertificationIMG = function(){
			console.log("製作證書PNG中");
			var data = new Object();
    		var today = new Date();
			data.id_ = parse[index].studentId;
			data.owner_ =  parse[index].studentName;
			data.date_ = today.getFullYear()+ " 年 " + (today.getMonth()+1) + " 月 " + today.getDate() + " 日" ;
			data.courceDate_ = " 於 " + parse[index].courseDate;
			data.courceName_ = parse[index].courseName;
			data.courceDuration_ = "全期共"+parse[index].couresDuration+"小時研習期滿，特此證明"; 
						
			$.post("/SLM2016/CertificationServlet",JSON.stringify(data))
			.done(function(imgData)
			{
				document.getElementById("certificationImg").setAttribute('src','data:image/png;base64,'+imgData);
				
				var saveData = new Object();
				saveData.saveDB = "save";
				saveData.studentId = parse[index].studentId;
				$.post("/SLM2016/CertificationServlet",JSON.stringify(saveData))
				.done(function(pdfData)
				{
					console.log("save");
				});
			});
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