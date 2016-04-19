var selectedIndexCount = 0;

function e_mail_preview(Name, Course) {
	var temp_name = document.createElement('temp');
	temp.previewBox.value = 'Hi ' + Name + ',\n' + ' 您好，歡迎報名' + Course
			+ '，以下是您的上課通知，請參考。\n' + '若有任何問題，歡迎隨時聯絡我們。\n' + '泰迪軟體 Erica'
}

function updateStudentCheckList(index) {
	var studentCheckList = document.getElementById("studentName");
	studentCheckList.innerHTML = "";

	for ( var student in studentArray[index]) {
		if (studentArray[index].hasOwnProperty(student)) {
			var studentName = studentArray[index][student];
			var checkbox = document.createElement("input");
			checkbox.type = "checkbox";
			checkbox.name = "checkbox_name";
			checkbox.value = studentName;
			checkbox.checked = true;
			studentCheckList.appendChild(checkbox);

			var label = document.createElement('label')
			label.htmlFor = studentName;
			label.appendChild(document.createTextNode(studentName));
			studentCheckList.appendChild(label);
			studentCheckList.appendChild(document.createElement("br"));
		}
	}
	var temp_name = document.createElement('temp');
	temp.previewBox.value = '請按下產生';
	document.temp.member.options.length = 0;
}

function generatePreviewMail() {
	selectedIndexCount = 0;
	var studentCheckList = document.getElementById("studentName").childNodes[1].firstChild;
	var inpText_Name = studentCheckList.parentNode;
	// Get students name
	var inptext_name = inpText_Name.parentNode.innerText;
	// Import students name into array for mail preview purpose
	var array_name = inptext_name.split('\n');
	var checkbox_list = document.getElementsByName('checkbox_name');
	for (i = 0; i < array_name.length - 1; i++) {
		document.temp.member.options[i] = null;
	}
	for (i = 0; i < array_name.length - 1; i++) {
		if (checkbox_list[i].checked) {
			document.temp.member.options[selectedIndexCount] = new Option(
					checkbox_list[i].value, checkbox_list[i].value);
			selectedIndexCount = selectedIndexCount + 1;
		}
	}
	var temp_name = document.createElement('temp');
	temp.previewBox.value = '請透過選取右邊名單來預覽信件';
}

// Trigger event when user click student name for preview input
function updateMailContent() {
	var courseCheckbox = document.getElementById("courseCheckbox").value;
	// check which one student be choose by user
	for (i = 0; i < selectedIndexCount; i++) {
		if (document.temp.member.options[i].selected)
			// Call function to update e-mail preview content
			e_mail_preview(document.temp.member.options[i].value, courseCheckbox);
	}
}

function sendmail() {
	var data = new Object();
	var data_buffer = [];
	var studentCheckList = document.getElementById("studentName").childNodes[1].firstChild;
	var inpText_Name = studentCheckList.parentNode;
	var inptext_name = inpText_Name.parentNode.innerText;
	var array_name = inptext_name.split('\n');
	var checkbox_list = document.getElementsByName('checkbox_name');
	// Return index to back end
	var checked = 0;
	for (i = 0; i < array_name.length - 1; i++) {
		if (checkbox_list[i].checked) {
			data_buffer.push(i);
			checked = 1;
		}
	}
	if (checked == 1) {
		data.indexes_ = data_buffer;
		$.post("http://localhost:8080/SLM2016/SendGmailServlet",
				JSON.stringify(data)).done(function(data) {
				window.alert(data);
		});
	}
	if (checked == 0)
		window.alert("Please choose Recipient!!");
}

function getClassStudentname() {
	$.get("http://localhost:8080/SLM2016/SendGmailServlet",	function(responseText) {
		document.myForm.courseCheckbox.options[selectedIndexCount] = new Option(
			responseText.className_,responseText.className_);
			studentArray[0] = responseText.students_;
	});
}