$(function () {
	function drawChart(graphHandler) {
		var jinengoChart = new JinengoChart($(this).data('type'), $(this).data('unit'), $(this).text(), '2012', graphHandler);

		jinengoChart.draw();
		$(".nav .categorie").removeClass("current");
	}
	
	// init event listener
	function initEvenListener() {
	
		$(".monthSum .btn").click(function() {
			drawChart(new GraphHandler("api/user/figures", "api/platform/figures"));
		});
		
		$(".monthAvg .btn").click(function() {
			drawChart(new GraphHandler("api/user/averages", "api/platform/averages"));
		});
		
		$(".transportation .btn").click(function() {
			drawChart(new PieHandler("api/user/transportation", "api/platform/transportation"));
		});
		
		$(".nav .categorie .headline").click(function() {
			$(".nav .categorie").removeClass("current");
			$(this).parent().addClass("current");
		});
		$('.nav .categorie').mouseleave(function() {
			$(this).removeClass("current");
		});
	}
	
	initEvenListener();
	
	//default start chart
	$(".monthSum .btn").first().trigger('click');
});
