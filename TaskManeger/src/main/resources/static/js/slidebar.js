
$(function() {
	var range1 = $('#range-1').val();
	var range2 = $("#range-2").val();
	$("#start-value").text(range1);
	$("#end-value").text(range2);
    $("#range-1").on("input change", function() {
        range1 = $('#range-1').val();
		$("#start-value").text(range1);
    })
    
    $("#range-2").on("input change", function() {
		range2 = $("#range-2").val();
    	$("#end-value").text(range2);
	})
    	
})