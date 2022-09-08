
$(function() {
	var range1 = $('#range-1').val();
	var range2 = $("#range-2").val();
	$("#phase1").text(range1);
	$("#phase2").text(range2);
    $("#range-1").on("input change", function() {
        range1 = $('#range-1').val();
        if (range1 <= 0) {
			range1 = 1;
		}
		$("#phase1").text(range1);
		$("#phase1").val(range1);
		if (Number(range1) >= Number(range2)) {
			range1 = Number(range2) - 1;
			if (range1 <= 0) {
				range1 = 1;
			}
	    	$("#phase1").text(range1);
	    	$("#phase1").val(range1);
	    	$("#range-1").val(range1);
		}
		if (Number(range1) >= Number(range2)) {
			range2 = Number(range1) + 1;
			if(range2 >= 100) {
				range2 = 99;
			}
	    	$("#phase2").text(range2);
	    	$("#phase2").val(range2);
	    	$("#range-2").val(range2);
		}
    });
    
    $("#range-2").on("input change", function() {
		range2 = $("#range-2").val();
		if (range2 >= 100) {
			range2 = 99;
		}
    	$("#phase2").text(range2);
    	$("#phase2").val(range2);
		if (Number(range1) >= Number(range2)) {
			range2 = Number(range1) + 1;
			if(range2 >= 100) {
				range2 = 99;
			}
	    	$("#phase2").text(range2);
	    	$("#phase2").val(range2);
	    	$("#range-2").val(range2);
		}
		if (Number(range1) >= Number(range2)) {
			range1 = Number(range2) - 1;
			if (range1 <= 0) {
				range1 = 1;
			}
	    	$("#phase1").text(range1);
	    	$("#phase1").val(range1);
	    	$("#range-1").val(range1);
		}
	});
});
$(function() {
	var startColor = $("#startColor").val();
	var middleColor = $("#middleColor").val();
	var endColor = $("#endColor").val();
	$(":root").css("--bar1", startColor);
	$(":root").css("--bar2", middleColor);
	$(":root").css("--bar3", endColor);
	$("#startColor").on("change", function() {
		startColor = $("#startColor").val();
		$(":root").css("--bar1", startColor);
	});
	
	$("#middleColor").on("change", function() {
		middleColor = $("#middleColor").val();
		$(":root").css("--bar2", middleColor);
	});
	
	$("#endColor").on("change", function() {
		endColor = $("#endColor").val();
		$(":root").css("--bar3", endColor);
	});
});
$(function() {
	$("#color1").on("click", function() {
		var startColor = "#00ff00";
		$("#startColor").val(startColor);
		$(":root").css("--bar1", startColor);
	});
	
	$("#color2").on("click", function() {
		var middleColor = "#ffff00";
		$("#middleColor").val(middleColor);
		$(":root").css("--bar2", middleColor);
	});
	
	$("#color3").on("click", function() {
		var endColor = "#ff0000";
		$("#endColor").val(endColor);
		$(":root").css("--bar3", endColor);
	});
});
   
$(function() {
		
	$('[name="select"]').on("click" , function() {
		var sorts = [$("#1").val(), $("#2").val(), $("#3").val(), $("#4").val(), $("#5").val()];
		var id = 1;
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

$(function() {
	$('#submit').mouseover(function() {
		var sort = $("#sort").val();
		if (!sort) {
			alert("分類が設定されていません");
		}
	});
});