JinengoChart = function(keyFigure, chartYear, chartText) {
	
	// start chart drawing chain
	var drawChart = function () {
		getUserData();
	};
	
	// get user data for a given figure
	var getUserData = function () {
		$.getJSON('api/user/figures', {keyFigure: keyFigure, year: chartYear}, function(userData, status) {
			getPlatformData(userData);
		});
	};
	
	// get data for overall platform user for a given figure
	var getPlatformData = function (userData) {
		$.getJSON('api/platform/figures', {keyFigure: keyFigure, year: chartYear}, function(platformData, status) {
			prepareData(userData, platformData);
		});
	};	  
	
	// prepare data to draw chart and set graph description
	var prepareData = function (userData, compareData) {
		var dataCategories = [],
			dataUser = [],
			dataPlatform = [],
			monthList = ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'];
		
		for (key in userData) {
			// convert month integer 1-12 to Jan-Dez
			dataCategories.push(monthList[userData[key][2] - 1]);
			
			// save property value for user
			dataUser.push(userData[key][0]);
			
			// save property value for platform sum
			if (compareData[key]) {
				dataPlatform.push(compareData[key][0]);
			} else {
				dataPlatform.push(0);
			}
		}
		hideLoader();
		var graphData = {
				dataUser: dataUser,
				dataPlatform: dataPlatform,
				dataCategories: dataCategories,
				chartYear: chartYear,
				chartText: chartText
		};
		graphChart(graphData);
	};
	drawChart();
};

function showLoader() {
	$('#loader').show();
	$('#container').hide();
}

function hideLoader() {
	$('#loader').hide();
	$('#container').show();
}

function initEvenListener() {
	
	// init event listener
	$(".figure-button").click(function() {
		showLoader();
		var jinengoChart = new JinengoChart($(this).data('type'), '2012', $(this).text());
	});
}

// init chart on DOM-ready
$(function () {
	
	initEvenListener();
	
	//default start chart
	var jinengoChart = new JinengoChart('sumEcoImpact', '2012', 'Eco-Impact');
});
