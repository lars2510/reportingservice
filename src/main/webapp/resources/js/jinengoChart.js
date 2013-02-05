/**
 * JinengoChart Class to handle json request to the jinengo api
 * Delegate the chart drawing mechanism to the chartHandler
 * 
 * Author: Lars Schüttemeyer
 */
var JinengoChart = function(keyFigure, chartUnit, chartText, chartYear, chartHandler) {
	var _self = this;
	
	// basic chart data
	_self.graphData = {
		chartUnit: chartUnit,
		chartText: chartText,
		chartYear: chartYear
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
				url: chartHandler.userApiUrl,
				type: "GET",
				data: {keyFigure: keyFigure, year: chartYear},
				dataType: "json"
			}),
			$.ajax({
				url: chartHandler.compareApiUrl,
				type: "GET",
				data: {keyFigure: keyFigure, year: chartYear},
				dataType: "json"
			}))
		.done(function(a1, a2){
			hideLoader();
			_self.graphData.userData = a1[0];
			_self.graphData.compareData = a2[0];
			chartHandler.draw(_self.graphData);
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