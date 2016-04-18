var j=0;

function generate(){
	context.clearRect(0, 0, canvas.width, canvas.height);
	var inpText_name=document.getElementById("inpText_Name").value;   
	var inpText_course=document.getElementById("inpText_Course").value;
	var inpText_year=document.getElementById("inpText_Year").value;
	var inpText_month=document.getElementById("inpText_Month").value;
	var inpText_day=document.getElementById("inpText_Day").value;
	var wordstyle=document.getElementById("sel").value;
	var ctx = myCanvas.getContext('2d');
	var imageObj = new Image();
	var x = 0;
    var y = 0;
    var width = 578;
    var height = 400;
    
    imageObj.onload = function() {
    	ctx.drawImage(imageObj, x,y ,width,height);
      	ctx.font = "20px " + wordstyle;
      	ctx.textAlign="right";
      	ctx.fillText(inpText_name  	,190, 120);
      	ctx.textAlign="center";
      	ctx.fillText(inpText_year 	,240, 122);
      	ctx.fillText(inpText_month 	,295, 122);
      	ctx.fillText(inpText_day 	,360, 122);
      	ctx.fillText(inpText_course ,279, 160);
      	ctx.fillText("12" ,223, 207);
      	ctx.font = "15px " + wordstyle;
      	ctx.fillText("104598007" ,485, 75);
      	ctx.fillText(checkedBoxes ,500, 160);
      	download(myCanvas);
      	
    };
	imageObj.src = 'Screenshot_2016-03-30_22.11.28.png';
}

function e_mail_preview(Name,Course){
var temp_name=document.createElement('temp');
temp.fahr.value=  
	'Hi ' 
+	Name
+	',\n'
+	' 您好，歡迎報名' 
+	Course
+	'，以下是您的上課通知，請參考。\n'
+ 	'若有任何問題，歡迎隨時聯絡我們。\n' 
+ 	'泰迪軟體 Erica' 
}


/*
function download(canvas) {
    var filename = 'Certificate.png';   
    var lnk = document.createElement('buffer'),e;
    
    lnk.download = filename;		
    lnk.href = canvas.toDataURL();	
    
    if (document.createEvent) {
        
        e = document.createEvent("MouseEvents");
        e.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        
        lnk.dispatchEvent(e);
        
    } else if (lnk.fireEvent) {
        
        lnk.fireEvent("onclick");
    }
}
*/


function renew(index){
	for(var i=0;i<department[index].length;i++)
		document.myForm.member.options[i]=new Option(department[index][i], department[index][i]);	// 設定新選項
	document.myForm.member.length=department[index].length;	// 刪除多餘的選項
}

function renew_mail(index){
	var s2=document.getElementById("inpText_Name_1");
	s2.innerHTML = "";

	for(var option in department[index])
	{
	 if (department[index].hasOwnProperty(option)) {
	var pair = department[index][option];
	var checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.name = "checkbox_name";
    checkbox.value = pair;
    checkbox.checked = true;
    s2.appendChild(checkbox);
   
    var label = document.createElement('label')
    label.htmlFor = pair;
    label.appendChild(document.createTextNode(pair));
    s2.appendChild(label);
    s2.appendChild(document.createElement("br"));
	 }
	}
	var temp_name=document.createElement('temp');
	temp.fahr.value= '請按下產生';
	document.temp.member.options.length = 0;
}

function generatemail(){
	j=0;
	var s2=document.getElementById("inpText_Name_1").childNodes[1].firstChild;
	var inpText_Name=s2.parentNode;
	//Get students name
	var inptext_name=inpText_Name.parentNode.innerText;
	//var innerHTMLteset = inpText_Name.parentNode.innerHTML;
	//Get number of students
	//var numberofstudent = (innerHTMLteset.split("</label><br>").length)-1;
	//Import students name into array for mail preview purpose
	var array_name = inptext_name.split('\n');
	var checkbox_list= document.getElementsByName('checkbox_name');
	for (i=0;i<array_name.length-1;i++) {
		document.temp.member.options[i] = null;
	}
	for(i=0;i<array_name.length-1;i++){
		if(checkbox_list[i].checked){
		document.temp.member.options[j]=new Option(checkbox_list[i].value, checkbox_list[i].value);
		j=j+1;}
	}
	var temp_name=document.createElement('temp');
	temp.fahr.value= '請透過選取右邊名單來預覽信件'; 
}

//Trigger event when user click student name for preview input
function renew_mailcontent(){
	    var inptext_course=document.getElementById("inpText_Course").value;
	    //check which one student be choose by user
	    for(i=0;i<j;i++){
	    	if(document.temp.member.options[i].selected)
	    //Call function to update e-mail preview content 
		e_mail_preview(document.temp.member.options[i].value,inptext_course);
	    }
}

function sendmail(){
	var data=new Object();
	var data_buffer=[];
	var s2=document.getElementById("inpText_Name_1").childNodes[1].firstChild;
	var inpText_Name=s2.parentNode;
	var inptext_name=inpText_Name.parentNode.innerText;
	var array_name = inptext_name.split('\n');
	var checkbox_list= document.getElementsByName('checkbox_name');
	//Return index to back end
	var checked=0;
	for(i=0;i<array_name.length-1;i++){
		if(checkbox_list[i].checked){
					
			data_buffer.push(i); 
			checked=1;
		}
	}
	if(checked==1){
		data.indexes_=data_buffer;
		console.log(data);
	$.post("http://localhost:8080/SLM2016/SendGmailServlet",JSON.stringify(data))
	.done(function(data)
	{
	
	});
	
	}
	
	if(checked==0)
		window.alert("Please choose Recipient!!");
}

function getclassname(){
	
	$.get("http://localhost:8080/SLM2016/SendGmailServlet", 
			function(responseText) {
		document.myForm.inpText_Course.options[j]=new Option(responseText.className_, responseText.className_);
		    });
}

function getstudentname(){
	
	$.get("http://localhost:8080/SLM2016/SendGmailServlet", 
    function(responseText) {
		department[0]=responseText.students_;	
      });
}


function showList() {
	var message = "顯示清單";
	document.getElementById("message").innerHTML = message;
	$
			.ajax({
				type : 'POST',
				contentType : "application/json; charset=UTF8",
				dataType : "json",
				// http://ilab.csie.ntut.edu.tw:8080/XX_hw11/spring/ajax/doPay
				// http://ilab.csie.ntut.edu.tw:8080/44_hw11/spring/ajax/doPay
				url : 'http://ilab.csie.ntut.edu.tw:8080/Final_44/spring/project/doCustomerList',
				data : JSON.stringify({
					name : $("#user").html()

				}),
				success : function(response) {
					$("#list").show();
					// console.log(response);
					$("#list")
							.html(
									"<table id=\"tablea\" border=\"1\" valign=\"middle\"><tr><td>訂單序號</td><td>訂單狀況</td><td>訂單使用者</td><td>訂單地址</td></tr></table>")
					$.each(response, function(k, v) {
						console.log(v);
						$("#tablea").append(
								"<tr><td>" + v.id + "</td><td>" + v.state
										+ "</td><td>" + v.customer
										+ "</td><td>" + v.address
										+ "</td></tr>");
					});
				}
			});
}