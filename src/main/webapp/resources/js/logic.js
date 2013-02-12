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
		
		var jinengoChart = new JinengoChart(graphData);
		jinengoChart.draw();
		
	}
	
	/**
	 * Hide seccond compare container for pie graph
	 */
	function hideCompContainer() {
		$("#compare-container").hide();
		$(".container").removeClass("type1of2");
	}
	
	/**
	 * save the graph settings
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
	 * init event handler for button with specific api url
	 * creates new instance of specific graph handler class
	 * this reference points to the button dom object and has several data attributes
	 */
	function initEventListener() {
	
		$(".btn.monthSum").click(function() {
			var graphData = setGraphData(this, '2012', new GraphHandler("api/user/figures", "api/platform/figures"));
			drawChart(graphData);
		});
		
		$(".btn.monthAvg").click(function() {
			var graphData = setGraphData(this, '2012', new GraphHandler("api/user/averages", "api/platform/averages"));
			drawChart(graphData);
		});
		
		$(".btn.transportation").click(function() {
			var graphData = setGraphData(this, '2012', new PieHandler("api/user/transportation", "api/platform/transportation"));
			drawChart(graphData);
		});
		
		$(".btn.advantages").click(function() {
			var graphData = setGraphData(this, '2012', new GraphAdvHandler("api/user/figures", "api/user/advantages"));
			drawChart(graphData);
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
	 * ajax request to get user data like user name
	 */
	function initUserDetails() {
		$.getJSON('api/user/details', function(userData, status) {
			$(".head h2").text("Hallo " + userData.name);
			$(".head span").text("Registriert am: " + userData.timeRegistered);
		});
	}
	
	/**
	 * ajax request to get users friends
	 */
	function initFriendList() {
		$.getJSON('api/user/friends', function(friendList, status) {
			var $friendList = $("#friend-list");
			for ( var i = 0; i < friendList.length; i++) {
				$friendList.append($("<li />").data("id", friendList[i].id).html(friendList[i].name));
			}
			$friendList.children().click(function() {
				GraphData.friendId = $(this).data("id");
				GraphData.handler.compareApiUrl = GraphData.handler.userApiUrl;
				drawChart(GraphData);
			});
		});
	}
	
	/**
	 * init graph handler
	 * draw default graph
	 */
	function init() {
		// initialize
		initUserDetails();
		initEventListener();
		initNavigationListener();
		initFriendList();
		
		//draw default chart
		$(".btn.monthSum").first().trigger('click');
	}
	init();
});
