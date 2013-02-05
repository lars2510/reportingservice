/**
 * Default JavaScript to init event listener and start the chart drawing process
 * Author: Lars Schüttemeyer
 */
$(function () {
	
	/**
	 * Create instance of Chart Handler and start drawing the chart
	 */
	function drawChart(btn, graphHandler) {
		
		var jinengoChart = new JinengoChart($(btn).data('type'), $(btn).data('unit'), $(btn).text(), '2012', graphHandler);
		jinengoChart.draw();
		
	}
	
	/**
	 * init event handler for button with specific api url
	 */
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

	/**
	 * init event handler to display navigation menue
	 */
	function initNavigationListener() {
		
		$(".nav .categorie .headline").click(function() {
			$(".nav .categorie").removeClass("current");
			$(this).parent().addClass("current");
		});
		
		$('.nav .categorie').mouseleave(function() {
			$(this).removeClass("current");
		});
		
	}
	
	// initialize
	initEventListener();
	initNavigationListener();
	
	//init default chart
	$(".btn.monthSum").first().trigger('click');
	
});
