function click_form() {
	$.get("http://localhost:8080/SLM2016/hello", 
	function(responseText) {
        $("#somediv").text(responseText.Date);
    });
}