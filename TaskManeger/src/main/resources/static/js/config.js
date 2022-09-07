
$(function() {
	var range1 = $('#range-1').val();
	var range2 = $("#range-2").val();
	$("#start-value").text(range1);
	$("#end-value").text(range2);
    $("#range-1").on("input change", function() {
        range1 = $('#range-1').val();
		$("#start-value").text(range1);
		$("#start-value").val(range1);
		if (Number(range1) >= Number(range2)) {
			range2 = Number(range1) + 1;
	    	$("#end-value").text(range2);
	    	$("#end-value").val(range2);
	    	$("#range-2").val(range2);
		}
    })
    
    $("#range-2").on("input change", function() {
		range2 = $("#range-2").val();
    	$("#end-value").text(range2);
    	$("#end-value").val(range2);
    	if (Number(range1) >= Number(range2)) {
			range1 = Number(range2) - 1;
	    	$("#start-value").text(range1);
	    	$("#start-value").val(range1);
	    	$("#range-1").val(range1);
		}
	})
   
$(function() {
		
			$('[name="select"]').on("click" , function() {
				var sorts = [$("#1").val(), $("#2").val(), $("#3").val(), $("#4").val(), $("#5").val()];
				var id = "";
				var sort = $('[name="select"]').val();
				for (let i = 1 ; i < 6; i++) {
					if (sort == sorts[i-1]) {
						id = i;
					}
				}
				$('[name="sort"]').val(sort);
				$('[name="id"]').val(id);
				$('[name="id"]').attr("id", id);
				console.log($('[name="id"]').attr("id"));
			});
		});
})