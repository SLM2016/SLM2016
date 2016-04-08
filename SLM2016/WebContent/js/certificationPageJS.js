function ClickGenerateButton() {
    var name = document.getElementById('studentName').value;
    var id = document.getElementById('certificationId').value;
	var data=new Object();
	data.id_=id;
	data.owner_=name;
	
	$.post("http://localhost:8080/SLM2016/CertificationServlet",JSON.stringify(data))
	.done(function(data)
	{
		document.getElementById("someImg").setAttribute('src','data:image/png;base64,'+data);
	});
}