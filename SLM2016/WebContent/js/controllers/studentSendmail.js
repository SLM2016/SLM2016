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
			function howManydays(D){
				var res = D.split("、");
				return res.length;
			}
			function setemailcontent(){
				
				
				var con="Hi "+
				parseMailData[index].studentName+",<br><br>"+
				"很開心這次和大家一起進行了"+howManydays(date.value)+"天的課程，希望透過上課的講解與實作練習能對"+parseMailData[index].courseName+"有更深的瞭解與應用的機會。<br><br>"+
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
			mailData.id_ = parseMailData[index].studentId;
			mailData.courseName_ = parseMailData[index].courseName;
			mailData.addresses_ = parseMailData[index].address;
			mailData.text_ = getcontent();
			
			if (confirm("是否確認寄送!?") == true){
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
		}
	  
		var setValue = function(){ 		
			$scope.isCertificationLoading = true;
			courseName.value = parseMailData[index].courseName;
    		studentName.value = parseMailData[index].studentName;
    		date.value = parseMailData[index].courseDate;
    		studentId.value = parseMailData[index].studentId;
    		couresDuration.value = parseMailData[index].couresDuration; 
    		
    		StudentInfoService.getCertificationInfo(JSON.stringify(parseMailData[index].studentId)).then(function(certificationData) {
    			var parse = JSON.parse(JSON.stringify(certificationData));   			
    			
    			if(parse[0].certification_img == ""){
    				makeCertification();   				
    			}  
    			else{
    				console.log("DB 有資料，直接顯示");
    				document.getElementById("certificationImg").setAttribute('src','data:image/png;base64,'+parse[0].certification_img);
    				$scope.isCertificationLoading = false;
    			}
            }, function(error) {
            	console.log('Get DB Data Has Error');
            	$scope.isCertificationLoading = false;
            });		
		}
		
		var numberChar = ["零","一","二","三","四","五","六","七","八","九"];
        var unitChar = ["","十"];
        
        function numberToChinese(number){
            var result = '';
            
            if(number === 0){
                return numberChar[0];
            }

            while(number > 0){
                var section = number % 100;
                result = sectionToChinese(section);
                number = Math.floor(number / 100);
            }
            if((result.length >= 2) && (result.indexOf("一") == 0)){
            	result = result.replace("一","");
            }
             
            return result;
        }
        
        function sectionToChinese(section){
            var tempChar = '', result = '';
            var unitCharIndex = 0;
            while(section > 0){
                var numberCharIndex = section % 10;
                if(numberCharIndex !==0){
                	tempChar = numberChar[numberCharIndex];
                    tempChar += unitChar[unitCharIndex]
                    result = tempChar + result;
                }             
                             
                unitCharIndex++;
                section = Math.floor(section / 10);
            }
           
            return result;
        }
		
		var makeCertification = function(){
			console.log("製作證書PNG中");
			var data = new Object();
    		var today = new Date();
			data.id_ = parseMailData[index].studentId;
			data.owner_ =  parseMailData[index].studentName;
			data.date_ = today.getFullYear()+ " 年 " + (today.getMonth()+1) + " 月 " + today.getDate() + " 日" ;
			data.courceDate_ = " 於 " + parseMailData[index].courseDate;
			data.courceName_ = parseMailData[index].courseName;
			data.courceDuration_ = "全期共"+parseMailData[index].couresDuration+"小時研習期滿，特此證明"; 

			$.post("/SLM2016/CertificationServlet",JSON.stringify(data))
			.done(function(imgData)
			{
				document.getElementById("certificationImg").setAttribute('src','data:image/png;base64,'+imgData);
				
				var saveData = new Object();
				saveData.saveDB = "save";
				saveData.studentId = parseMailData[index].studentId;
				$.post("/SLM2016/CertificationServlet",JSON.stringify(saveData))
				.done(function(pdfData)
				{
					console.log("save");
					$scope.isCertificationLoading = false;
					$scope.$apply();
				});				
			});
		}
		
		var ClickNextButton = function(){	

			if(++index <= parseMailData.length-1){	
				previousButton.disabled = "";
				setValue();
			}	
			if(index == parseMailData.length-1){
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
    		$scope.isCertificationLoading = true;
    		parseMailData = JSON.parse(StudentInfoService.getStudentSendMailData());
    		
    		StudentInfoService.getSendMailInfo(JSON.stringify(parseMailData)).then(function(courseData) {
                var parse = JSON.parse(JSON.stringify(courseData));
                var dataInterval = (parse.length) / parseMailData.length;
                for(var i = 0, j = 0; i < parseMailData.length; i++){
                	if( (j < parse.length) && (parse[j].id == parseMailData[i].courseId)){
                		var duration = Number(parse[j].duration);
                		var date = new Date(parse[j].date);
                		parseMailData[i].courseName = parse[j].name;
                    	parseMailData[i].couresDuration = numberToChinese(duration);
                    	parseMailData[i].courseDate = date.getFullYear()+ " 年 " + (date.getMonth()+1) + " 月 ";
                		for(var k = 0; k < dataInterval; k++){
                			var date = new Date(parse[j].date);      
                			if(k != dataInterval-1){
                				parseMailData[i].courseDate += date.getDate() + "、";
                			}
                			else{
                				parseMailData[i].courseDate += date.getDate() + " 日 ";
                			}                       	
                        	j++;
                		}             		
                	}       
                }  
                setValue();
            }, function(error) {
            	console.log('Get DB Data Has Error');
            })                     
             		
    		if(parseMailData.length-1 > 0){
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
 
    	var parseMailData;
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
    	
    	$scope.isCertificationLoading = false;
    	$scope.Send = Send;
    	$scope.ClickNextButton = ClickNextButton;
    	$scope.ClickPreviousButton = ClickPreviousButton;
    	
    	
        init();
		
}]);