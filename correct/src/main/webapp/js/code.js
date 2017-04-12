$(document).ready(function(){
	$("#codeImage").click(function() {
		$("#codeImage").attr("src", "code?noEffect=" + Math.random());
	});
});
