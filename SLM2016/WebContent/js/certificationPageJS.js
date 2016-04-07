function ClickGenerateButton() {
	$.get("http://localhost:8080/SLM2016/CertificationServlet", function(
			responseText) {
		document.getElementById('someImg').setAttribute('src',
				'data:image/png;base64,' + responseText);

	});
	document.getElementById('lastButton').style.display = "inline";
	document.getElementById('nextButton').style.display = "inline";
	document.getElementById('generateButton').style.display = "none";
}

function ClickLastButton() {
}

function ClickNextButton() {
}