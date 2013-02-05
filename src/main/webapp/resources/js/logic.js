$(function () {
	
	function drawChart(btn, graphHandler) {
		console.log($(btn).data('type') + " - " + $(btn).data('unit') + " - " + $(btn).text())
		var jinengoChart = new JinengoChart($(btn).data('type'), $(btn).data('unit'), $(btn).text(), '2012', graphHandler);

		jinengoChart.draw();
		
	}
	
	// init event listener
	function initEventListener() {
	
		$(".btn.monthSum").click(function() {
			drawChart(this, new GraphHandler("api/user/figures", "api/platform/figures"));
		});
		
		$(".btn.monthAvg").click(function() {
			drawChart(this, new GraphHandler("api/user/averages", "api/platform/averages"));
		});
		
		$(".btn.transportation").click(function() {
			drawChart(this, new PieHandler("api/user/transportation", "api/platform/transportation"));
		});
	
	}

	function initNavigationListener() {
		
		$(".nav .categorie .headline").click(function() {
			$(".nav .categorie").removeClass("current");
			$(this).parent().addClass("current");
		});
		
		$('.nav .categorie').mouseleave(function() {
			$(this).removeClass("current");
		});
		
	}
	
	initEventListener();
	initNavigationListener();
	
	//default start chart
	$(".btn.monthSum").first().trigger('click');
	
});
