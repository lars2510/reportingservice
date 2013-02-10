/**
 * JinengoChart Class to handle json request to the jinengo api
 * Delegate the chart drawing mechanism to the chartHandler
 * 
 * Author: Lars Schüttemeyer
 */
var JinengoChart = function(graphData) {
	var _self = this;
	
	// basic chart data
	_self.graphData = {
		chartUnit: graphData.unit,
		chartText: graphData.text,
		chartYear: graphData.year
	};
	
	// start the drawing chain
	_self.draw = function () {
		showLoader();
		getData();
	};
	
	// make ajax calls to jinengo api to get the data
	var getData = function () {
		
		$.when(
			$.ajax({
				url: graphData.handler.userApiUrl,
				type: "GET",
				data: {keyFigure: graphData.type, year: graphData.year},
				dataType: "json"
			}),
			$.ajax({
				url: graphData.handler.compareApiUrl,
				type: "GET",
				data: {keyFigure: graphData.type, year: graphData.year},
				dataType: "json"
			}))
		.done(function(a1, a2){
			hideLoader();
			_self.graphData.userData = a1[0];
			_self.graphData.compareData = a2[0];
			graphData.handler.draw(_self.graphData);
		})
		.fail(function(err) { 
			console.log("Es konnte keine Verbindung zur Jinengo API aufgebaut werden. Statuscode " + err.status);
		});
	};
	
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
	
};