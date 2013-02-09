/**
 * Default JavaScript to init event listener and start the chart drawing process
 * Author: Lars Schüttemeyer
 */
$(function () {
	
	/**
	 * Create instance of Chart Handler and start drawing the chart
	 */
	function drawChart(btn, graphHandler) {
		$('.nav .categorie').removeClass("current");
		var jinengoChart = new JinengoChart($(btn).data('type'), $(btn).data('unit'), $(btn).text(), '2012', graphHandler);
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
	 * init event handler for button with specific api url
	 */
	function initEventListener() {
	
		$(".btn.monthSum").click(function() {
			hideCompContainer();
			drawChart(this, new GraphHandler("api/user/figures", "api/platform/figures"));
		});
		
		$(".btn.monthAvg").click(function() {
			hideCompContainer();	
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
		
		$('.nav .categorie').mouseenter(function() {
			$(this).addClass("current");
		});
		
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
	 * ajax request to get user data like user name
	 */
	function initFriendList() {
		$.getJSON('api/user/friends', function(friendList, status) {
			var $friendList = $("#friend-list");
			for ( var i = 0; i < friendList.length; i++) {
				$friendList.append($("<li />").data("id", friendList[i].id).html(friendList[i].name));
			}
			$friendList.children().click(function() {
				console.log($(this).data("id"));
			});
		});
	}
	
	// initialize
	initUserDetails();
	initEventListener();
	initNavigationListener();
	initFriendList();
	
	//init default chart
	$(".btn.monthSum").first().trigger('click');
	
});
