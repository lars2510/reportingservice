/**
 * Default JavaScript to init event listener and start the chart drawing process
 * Author: Lars Schüttemeyer
 */
$(function () {
	
	// graph data object to save graph preferences
	var GraphData = {};
	
	/**
	 * Create instance of Chart Handler and start drawing the chart
	 */
	function drawChart(graphData) {
		hideCompContainer();
		$('.nav .categorie').removeClass("current");
		
		// draw chart with a deep copy of the graph data
		var graphDataCpy = $.extend(true, {}, graphData);
		var jinengoChart = new JinengoChart(graphDataCpy);
		jinengoChart.draw();
		
		// reset friend name
		delete GraphData.friendName;
	}
	
	/**
	 * Hide seccond compare container for pie graph
	 */
	function hideCompContainer() {
		$("#compare-container").hide();
		$(".container").removeClass("type1of2").removeClass("differentSize");
	}
	
	/**
	 * Save the graph settings
	 */
	function setGraphData(btn, year, graphHandler) {
		GraphData.type = $(btn).data('type');
		GraphData.unit = $(btn).data('unit'); 
		GraphData.text = $(btn).text();
		GraphData.year = year;
		GraphData.handler = graphHandler;
		return GraphData;
	}
	
	/**
	 * Reset friend html content
	 */
	function resetFriendList() {
		$(".friend-list-hd .headline").html("Vergleiche dich mit deinen Freunden");
		$(".friend-list-hd").show();
	}
	
	/**
	 * init event handler for button with specific api url
	 * creates new instance of specific graph handler class
	 * this reference points to the button dom object and has several data attributes
	 */
	function initEventListener() {
	
		$(".btn.monthSum").click(function() {
			var graphData = setGraphData(this, '2012', new GraphHandler("api/user/figures", "api/platform/figures"));
			drawChart(graphData);
			resetFriendList();
		});
		
		$(".btn.monthAvg").click(function() {
			var graphData = setGraphData(this, '2012', new GraphHandler("api/user/averages", "api/platform/averages"));
			drawChart(graphData);
			resetFriendList();
		});
		
		$(".btn.transportation").click(function() {
			var graphData = setGraphData(this, '2012', new PieHandler("api/user/transportation", "api/platform/transportation"));
			drawChart(graphData);
			resetFriendList();
		});
		
		$(".btn.balance").click(function() {
			var graphData = setGraphData(this, '2012', new BalanceHandler("api/user/balance", "api/user/balance"));
			drawChart(graphData);
			$(".friend-list-hd").hide();
		});
	}

	/**
	 * init event handler to display navigation menue
	 */
	function initNavigationListener() {
		
		// For Touch Device
		$(".nav .categorie .headline").click(function() {
			$(".nav .categorie").removeClass("current");
			$(this).parent().addClass("current");
		});

		// For Mouse Device
		$('.nav .categorie').mouseleave(function() {
			$(this).removeClass("current");
		});
		
		$('.nav .categorie').mouseenter(function() {
			$(this).addClass("current");
		});
		
		// Friends Menue
		$('.friend-list-hd').mouseleave(function() {
			$('#friend-list').removeClass("current");
		});
		
		$('.friend-list-hd').mouseenter(function() {
			$('#friend-list').addClass("current");
		});
	}
	
	/**
	 * ajax request to get user data like user name and trigger default chart
	 */
	function initMainData() {
		$.getJSON('api/user/details', function(userData, status) {
			$(".head h2").text("Hallo " + userData.name + "! Erfahre mehr über dein Fahrverhalten:");
			
			GraphData.userName = userData.name;
			//draw default chart
			$(".btn.monthSum").first().trigger('click');
		});
	}
	
	/**
	 * ajax request to get users friends
	 */
	function initFriendList() {
		$.getJSON('api/user/friends', function(friendList, status) {
			// get the friend dom object
			var $friendList = $("#friend-list");
			
			// add all friends to html list
			for ( var i = 0; i < friendList.length; i++) {
				$friendList.append($("<li />").data("id", friendList[i].id).html(friendList[i].name));
			}
			
			// Event handler, draw compare chart depending on friend id if friend is clicked
			$friendList.children().click(function() {
				GraphData.friendId = $(this).data("id");
				GraphData.friendName = $(this).html();
				GraphData.handler.compareApiUrl = GraphData.handler.userApiUrl;
				$(".friend-list-hd .headline").html("Vergleich mit: " + GraphData.friendName);
				drawChart(GraphData);
			});
		});
	}
	
	/**
	 * init graph handler by self invoking function
	 * draw default graph
	 */
	(function init() {
		initMainData();
		initEventListener();
		initNavigationListener();
		initFriendList();
	})();
});
