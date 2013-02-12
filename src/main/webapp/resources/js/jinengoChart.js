/**
 * JinengoChart Class to handle json request to the jinengo api
 * Delegate the chart drawing mechanism to the chartHandler
 * 
 * Author: Lars Schüttemeyer
 */
var JinengoChart = function(graphData) {
	
	// show loader while waiting for data
	var showLoader = function () {
		$('#loader').show();
		$('#container').hide();
	};
	
	// hide loader
	var hideLoader = function () {
		$('#loader').hide();
		$('#container').show();
	};
	
	// start the drawing chain
	this.draw = function () {
		showLoader();
		getData();
	};
	
	// make ajax calls to jinengo api to get the data
	var getData = function () {
		
		var requestData = {
				keyFigure: graphData.type, 
				year: graphData.year
			};
		
		// clone request Data properties and add friend id if available
		var friendData = $.extend(true, {}, requestData);
		if (graphData.friendId) {
			friendData.friendId = graphData.friendId;
		}
		
		$.when(
			$.ajax({
				url: graphData.handler.userApiUrl,
				type: "GET",
				data: requestData,
				dataType: "json"
			}),
			$.ajax({
				url: graphData.handler.compareApiUrl,
				type: "GET",
				data: friendData,
				dataType: "json"
			})
		).done(function(a1, a2){
			hideLoader();
			graphData.userData = a1[0];
			graphData.compareData = a2[0];
			graphData.handler.draw(graphData);
		}).fail(function(err) { 
			console.log("Es konnte keine Verbindung zur Jinengo API aufgebaut werden. Statuscode " + err.status);
		});
	};
	
};