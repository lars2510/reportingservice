/**
 * PieHandler Class to draw a pie chart depending on the given data
 * Init with api urls
 * 
 * Author: Lars Schüttemeyer
 */

// PieHandler Class default constructor
function PieHandler (userApiUrl, compareApiUrl) {
	this.userApiUrl = userApiUrl;
	this.compareApiUrl = compareApiUrl;
}

// extend the pie class prototybe object
PieHandler.prototype = {

	// prepare data and start chart drawing, change html class properties to draw two pies
	draw: function(graphData) {
		
		// prepare data and html container to show pie chart
		var preparedData = this.prepareData(graphData);
		$("#compare-container").show();
		$(".container").addClass("type1of2");
		
		// draw left pie
		this.drawChart(preparedData, preparedData.prepUserData, 'container', graphData.userName);
		
		// draw right pie
		var chartName = graphData.friendName ? graphData.friendName : 'Plattformdurchschnitt';
		this.drawChart(preparedData, preparedData.prepCompareData, 'compare-container', chartName);
	},
	
	// optional individual color setting 
	color: {
		"Car Sharing": "rgba(200,100,30,.8)",
		"Fernverkehr": "rgba(20,80,120,.8)",
		"Human Powered": "rgba(20,200,60,.8)",
		"ÖPNV": "rgba(90,60,200,.8)",
		"PKW (Privat)": "rgba(200,20,30,.8)"
	},
	
	// prepare data to draw chart and set graph description
	prepareData: function (graphData) {
		
		var color;
		graphData.prepUserData = [];
		graphData.prepCompareData = [];
		
		for (var i = 0; i < graphData.userData.length; i++) {
			color = this.color[graphData.userData[i][2]];
			graphData.prepUserData.push({
                name: graphData.userData[i][2],
                y: graphData.userData[i][0],
                color: color
			});
		}
		
		for (var i = 0; i < graphData.compareData.length; i++) {
			color = this.color[graphData.compareData[i][2]];
			graphData.prepCompareData.push({
                name: graphData.compareData[i][2],
                y: graphData.compareData[i][0],
                color: color
			});
		}
		
		return graphData;
	},

	// use highchart lib to draw the chart with the given data
	drawChart: function (graphData, graphValues, container, chartName) {
		
	    new Highcharts.Chart({
	        chart: {
	            renderTo: container,
	            plotBackgroundColor: false,
	            plotBorderWidth: null,
	            plotShadow: true
	        },
	        title: {
	            text: graphData.text + " <b>" + chartName + "</b><br /> (" + graphData.unit + ")"
	        },
	        tooltip: {
	            pointFormat: '{point.percentage}%',
	            percentageDecimals: 1
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: false
	                },
	                showInLegend: true
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: graphData.text,
	            data: graphValues
	        }]
	    });
	}
};
