$(function() {
	$('#submit').mouseover(function() {
		var date = $("#date").val();
		var task = $("#task").val();
		if (!date && !task) {
			alert("課題と期日が設定されていません");			
		} else if (!task){
			alert("課題が設定されていません");
		} else if (!date) {
			alert("期日が設定されていません");
		}
	});
});